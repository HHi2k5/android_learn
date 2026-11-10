// Mở tracker như một app desktop: server chạy ngầm + cửa sổ riêng (Edge/Chrome chế độ --app).
// Chạy:  node tracker/app.js   (hoặc bấm đúp lối tắt do tracker/tao-shortcut.ps1 tạo trên Desktop)
//
// - Đóng cửa sổ app thì server tự tắt.
// - Nếu server đã chạy sẵn (vd đang chạy `node tracker/server.js`) thì chỉ mở thêm cửa sổ.

const { spawn } = require('node:child_process');
const fs = require('node:fs');
const path = require('node:path');

const PORT = Number(process.env.PORT) || 4321;
const APP_URL = `http://127.0.0.1:${PORT}`;
// Hồ sơ trình duyệt riêng: cửa sổ app là một tiến trình độc lập, đóng nó là biết để tắt server.
const PROFILE = path.join(__dirname, '.app-profile');

async function isUp() {
  try {
    const r = await fetch(`${APP_URL}/api/state`, { signal: AbortSignal.timeout(1000) });
    return r.ok;
  } catch {
    return false;
  }
}

function findBrowser() {
  const roots = [process.env['ProgramFiles(x86)'], process.env.ProgramFiles, process.env.LOCALAPPDATA];
  const exes = ['Microsoft\\Edge\\Application\\msedge.exe', 'Google\\Chrome\\Application\\chrome.exe'];
  for (const exe of exes) {
    for (const root of roots) {
      const p = root && path.join(root, exe);
      if (p && fs.existsSync(p)) return p;
    }
  }
  return null;
}

// Trả về tiến trình cửa sổ app, hoặc null nếu phải mở bằng trình duyệt mặc định.
function openWindow(opts) {
  const browser = findBrowser();
  if (!browser) {
    spawn('cmd', ['/c', 'start', '', APP_URL], { detached: true, stdio: 'ignore' }).unref();
    return null;
  }
  const args = [
    `--app=${APP_URL}`,
    `--user-data-dir=${PROFILE}`,
    '--no-first-run',
    '--no-default-browser-check',
    '--window-size=980,860',
  ];
  return spawn(browser, args, { stdio: 'ignore', ...opts });
}

async function run() {
  require('./server.js');
  for (let i = 0; i < 50 && !(await isUp()); i++) await new Promise((r) => setTimeout(r, 100));
  const win = openWindow();
  if (!win) return; // không có Edge/Chrome: server chạy ngầm tới khi tắt máy hoặc tự kill
  const t0 = Date.now();
  // Thoát quá nhanh = trình duyệt giao cửa sổ cho tiến trình có sẵn -> không biết khi nào đóng, giữ server.
  win.on('exit', () => { if (Date.now() - t0 > 5000) process.exit(0); });
}

async function main() {
  if (process.argv.includes('--run')) return run();
  if (await isUp()) {
    const win = openWindow({ detached: true });
    if (win) win.unref();
    return;
  }
  // Tự chạy lại ở chế độ ẩn (không có cửa sổ console) rồi thoát ngay.
  spawn(process.execPath, [__filename, '--run'], { detached: true, windowsHide: true, stdio: 'ignore' }).unref();
}

main().catch((e) => { console.error(e); process.exit(1); });
