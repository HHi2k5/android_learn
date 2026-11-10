// App theo dõi lộ trình học — server nhỏ, không cần cài thư viện.
// Chạy:  node tracker/server.js   rồi mở http://127.0.0.1:4321
//
// - Tiến độ (tick) được đọc/ghi thẳng vào LO_TRINH.md  ->  [ ] <-> [x]
// - Ngày bắt đầu, giờ học, nhật ký tuần lưu ở tracker/data.json

const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');

const ROOT = path.resolve(__dirname, '..');
const ROADMAP = path.join(ROOT, 'LO_TRINH.md');
const DATA = path.join(__dirname, 'data.json');
const INDEX = path.join(__dirname, 'index.html');
const PORT = Number(process.env.PORT) || 4321;
const HOST = '127.0.0.1';

const CHECKBOX = /^(\s*-\s+\[)( |x|X)(\]\s+)(.*)$/;
const DATE = /^\d{4}-\d{2}-\d{2}$/;

// ---------- Đọc LO_TRINH.md thành cấu trúc giai đoạn / nhóm / mục ----------

function weekOf(s, inlineOnly) {
  const re = inlineOnly
    ? /^\*\*Tuần\s+(\d+)(?:\s*[–-]\s*(\d+))?/
    : /Tuần\s+(\d+)(?:\s*[–-]\s*(\d+))?/;
  const m = re.exec(s);
  return m ? { from: +m[1], to: +(m[2] || m[1]) } : null;
}

function parseRoadmap(text) {
  const lines = text.split(/\r?\n/);
  const stages = [];
  let title = '';
  let stage = null;
  let group = null;
  let lastWeek = null;

  const newGroup = (name) => {
    group = { title: name, week: weekOf(name, false), items: [], notes: [] };
    if (group.week) lastWeek = group.week;
    stage.groups.push(group);
  };

  lines.forEach((line, i) => {
    let m;
    if (!title && (m = /^#\s+(.+)/.exec(line))) {
      title = m[1].trim();
      return;
    }
    if ((m = /^##\s+Giai đoạn\s+(\d+)\s*[—–-]\s*(.+)$/.exec(line))) {
      const full = m[2].trim();
      const dm = /^(.*?)\s*\(([^)]*)\)\s*$/.exec(full);
      stage = {
        n: +m[1],
        title: dm ? dm[1] : full,
        duration: dm ? dm[2] : '',
        optional: /tu[ỳù]\s*chọn/i.test(full),
        groups: [],
      };
      stages.push(stage);
      group = null;
      lastWeek = stage.n === 0 ? { from: 0, to: 0 } : null;
      return;
    }
    if (/^##\s/.test(line)) {
      stage = null;
      group = null;
      return;
    }
    if (!stage) return;

    // Tiêu đề nhóm: "### ..." hoặc dòng bắt đầu bằng "**...**" (có thể kèm chú thích phía sau).
    if ((m = /^###\s+(.+)$/.exec(line)) || (m = /^\*\*([^*]+[^*:])\*\*\s*(.*)$/.exec(line))) {
      newGroup(m[1].trim());
      if (m[2]) group.notes.push(m[2].trim());
      return;
    }
    if ((m = CHECKBOX.exec(line))) {
      if (!group) newGroup('');
      const text = m[4];
      const own = weekOf(text, true);
      if (own) lastWeek = own;
      group.items.push({
        line: i,
        text,
        checked: m[2] !== ' ',
        week: own || group.week || lastWeek,
      });
      return;
    }
    if (group && (m = /^\s*-\s+(.+)$/.exec(line))) {
      group.notes.push(m[1]);
    }
  });

  for (const s of stages) s.groups = s.groups.filter((g) => g.items.length);
  return { title, stages: stages.filter((s) => s.groups.length) };
}

// ---------- Dữ liệu phụ (data.json) ----------

function loadData() {
  const base = { startDate: null, weeklyTarget: [10, 15], hours: {}, journal: {} };
  try {
    const d = JSON.parse(fs.readFileSync(DATA, 'utf8'));
    return { ...base, ...d, hours: d.hours || {}, journal: d.journal || {} };
  } catch (e) {
    if (e.code === 'ENOENT') return base;
    throw new Error('Không đọc được tracker/data.json: ' + e.message);
  }
}

function writeAtomic(file, content) {
  const tmp = file + '.tmp';
  fs.writeFileSync(tmp, content, 'utf8');
  fs.renameSync(tmp, file);
}

const saveData = (d) => writeAtomic(DATA, JSON.stringify(d, null, 2) + '\n');

function state() {
  return { roadmap: parseRoadmap(fs.readFileSync(ROADMAP, 'utf8')), data: loadData() };
}

class BadRequest extends Error {}

// ---------- Các thao tác ----------

const actions = {
  '/api/toggle'(b) {
    const raw = fs.readFileSync(ROADMAP, 'utf8');
    const eol = raw.includes('\r\n') ? '\r\n' : '\n';
    const lines = raw.split(eol);
    const m = Number.isInteger(b.line) ? CHECKBOX.exec(lines[b.line] || '') : null;
    if (!m || m[4] !== b.text) {
      throw new BadRequest('LO_TRINH.md đã thay đổi, đang tải lại.');
    }
    lines[b.line] = m[1] + (b.checked ? 'x' : ' ') + m[3] + m[4];
    writeAtomic(ROADMAP, lines.join(eol));
  },

  '/api/hours'(b) {
    const h = Number(b.hours);
    if (!DATE.test(b.date) || !Number.isFinite(h) || h < 0 || h > 24) {
      throw new BadRequest('Ngày hoặc số giờ không hợp lệ (0–24).');
    }
    const d = loadData();
    if (h === 0) delete d.hours[b.date];
    else d.hours[b.date] = Math.round(h * 100) / 100;
    saveData(d);
  },

  '/api/journal'(b) {
    if (!DATE.test(b.week)) throw new BadRequest('Tuần không hợp lệ.');
    const clean = (s) => String(s || '').slice(0, 4000).trim();
    const entry = { learned: clean(b.learned), stuck: clean(b.stuck), next: clean(b.next) };
    const d = loadData();
    if (entry.learned || entry.stuck || entry.next) d.journal[b.week] = entry;
    else delete d.journal[b.week];
    saveData(d);
  },

  '/api/settings'(b) {
    if (b.startDate !== null && !DATE.test(b.startDate)) {
      throw new BadRequest('Ngày bắt đầu không hợp lệ.');
    }
    const d = loadData();
    d.startDate = b.startDate;
    saveData(d);
  },
};

// ---------- HTTP ----------

function send(res, code, body, type = 'application/json; charset=utf-8') {
  res.writeHead(code, { 'Content-Type': type, 'Cache-Control': 'no-store' });
  res.end(typeof body === 'string' ? body : JSON.stringify(body));
}

function readBody(req) {
  return new Promise((resolve, reject) => {
    let size = 0;
    const chunks = [];
    req.on('data', (c) => {
      size += c.length;
      if (size > 100_000) {
        reject(new BadRequest('Dữ liệu quá lớn.'));
        req.destroy();
      } else chunks.push(c);
    });
    req.on('end', () => {
      try {
        resolve(JSON.parse(Buffer.concat(chunks).toString('utf8') || '{}'));
      } catch {
        reject(new BadRequest('JSON không hợp lệ.'));
      }
    });
    req.on('error', reject);
  });
}

const server = http.createServer(async (req, res) => {
  const allowed = [`127.0.0.1:${PORT}`, `localhost:${PORT}`];
  try {
    // Chỉ phục vụ cho chính máy này (chặn trang web khác gọi vào).
    if (!allowed.includes(req.headers.host)) return send(res, 403, { error: 'Forbidden' });
    const url = req.url.split('?')[0];

    if (req.method === 'GET' && (url === '/' || url === '/index.html')) {
      return send(res, 200, fs.readFileSync(INDEX, 'utf8'), 'text/html; charset=utf-8');
    }
    if (req.method === 'GET' && url === '/api/state') return send(res, 200, state());

    if (req.method === 'POST' && actions[url]) {
      const origin = req.headers.origin;
      const json = (req.headers['content-type'] || '').includes('application/json');
      if (!json || (origin && !allowed.includes(origin.replace(/^https?:\/\//, '')))) {
        return send(res, 403, { error: 'Forbidden' });
      }
      actions[url](await readBody(req));
      return send(res, 200, state());
    }
    send(res, 404, { error: 'Không tìm thấy.' });
  } catch (e) {
    if (!(e instanceof BadRequest)) console.error(e);
    send(res, e instanceof BadRequest ? 400 : 500, { error: e.message });
  }
});

server.listen(PORT, HOST, () => {
  console.log(`App lộ trình đang chạy: http://${HOST}:${PORT}  (Ctrl+C để dừng)`);
});
