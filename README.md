# android_learn

Học Android: Java → Kotlin → Jetpack Compose → app ô tô (Android Auto / Android Automotive OS).

- [LO_TRINH.md](LO_TRINH.md) — lộ trình 30 tuần
- [01-java/tuan1](01-java/tuan1/BAI_HOC.md) — Java tuần 1: bài học, ví dụ, bài tập
- `Kotlin_learn` — project Kotlin thử nghiệm

## App theo dõi lộ trình

App nhỏ chạy trên máy (thư mục `tracker/`) để tick tiến độ, xem chi tiết từng task, ghi giờ học và nhật ký tuần. Không dùng thư viện ngoài nên **không cần `npm install`**.

### Cần có

- **Node.js 18 trở lên** — kiểm tra bằng `node -v`
- **Windows + Edge hoặc Chrome** nếu muốn mở như app desktop (cách 1). Cách 2 chạy được trên mọi hệ điều hành.

### Cách 1 — Mở như app desktop (Windows)

Sau khi clone, chạy **một lần** trong thư mục repo để tạo icon và lối tắt **Lo trinh Android** trên Desktop:

```bash
powershell -ExecutionPolicy Bypass -File tracker\tao-shortcut.ps1
```

Từ đó chỉ cần bấm đúp lối tắt:

- Server chạy ngầm, tracker mở trong một cửa sổ riêng (không có thanh địa chỉ hay tab).
- Đóng cửa sổ thì server tự tắt.
- Nếu máy không có Edge/Chrome, tracker mở trong trình duyệt mặc định và server không tự tắt.

Không muốn tạo lối tắt thì chạy thẳng launcher:

```bash
node tracker/app.js
```

**Ghim vào taskbar:** chuột phải vào lối tắt trên Desktop → *Show more options* → *Pin to taskbar*. Đừng ghim từ cửa sổ app đang mở — icon đó chỉ mở trình duyệt mà không khởi động server.

**Chạy lại `tao-shortcut.ps1` khi:** chuyển thư mục repo sang chỗ khác, cài lại Node.js ở đường dẫn khác, hoặc lỡ xoá lối tắt.

### Cách 2 — Chạy server rồi mở bằng trình duyệt

```bash
node tracker/server.js
```

Mở http://127.0.0.1:4321 — bấm Ctrl+C trong terminal để dừng. Đổi cổng bằng biến môi trường `PORT` (áp dụng cho cả hai cách).

### Dùng app

- **Hôm nay:** đang ở tuần mấy của kế hoạch, các việc tiếp theo, giờ học tuần này. Lần đầu mở thì đặt ngày bắt đầu ở đây.
- **Lộ trình:** toàn bộ các giai đoạn; bấm **Chi tiết (n)** dưới mỗi task để xem học gì, thực hành gì và điều kiện "Xong khi".
- **Nhật ký & giờ học:** mỗi tuần 3 dòng — đã học gì, vướng gì, tuần sau làm gì.

### Dữ liệu nằm ở đâu

- Tick mục nào thì app ghi thẳng `[x]` vào `LO_TRINH.md`. Sửa file bằng tay cũng được, app đọc lại khi quay về cửa sổ.
- Chi tiết của task là các gạch đầu dòng **thụt vào** ngay dưới dòng `- [ ]` trong `LO_TRINH.md` — muốn thêm/sửa chi tiết thì sửa ở đó.
- Ngày bắt đầu, giờ học, nhật ký tuần lưu ở `tracker/data.json`.
- Cả hai file đều nằm trong repo: **commit + push** để giữ tiến độ, **pull** trên máy khác để đồng bộ.
- `tracker/.app-profile/` là hồ sơ trình duyệt riêng của cửa sổ app, đã được `.gitignore` bỏ qua; xoá đi cũng không sao.

### Gặp lỗi

- **Bấm lối tắt không thấy gì:** chạy `node tracker/app.js --run` trong terminal để xem lỗi (thường là thiếu Node.js hoặc cổng 4321 đang bị chương trình khác dùng).
- **PowerShell chặn script:** dùng đúng lệnh có `-ExecutionPolicy Bypass` ở trên.
- **Cửa sổ báo không kết nối được:** server chưa chạy — đóng cửa sổ rồi mở lại bằng lối tắt.

## Java

Chạy file Java (cần JDK 17+):

```bash
java Ngay1_BienVaKieu.java
```
