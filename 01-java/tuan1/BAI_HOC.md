# Java – Tuần 1: Cú pháp và Lập trình hướng đối tượng (OOP)

**Mục tiêu cuối tuần:** tự viết được một chương trình Java nhỏ có nhiều class, dùng kế thừa và interface.

**Thời lượng:** 5 buổi, mỗi buổi 2–3 giờ (1 giờ đọc + chạy ví dụ, 1–2 giờ làm bài tập).

## Cách chạy code

Máy bạn có Java 23 nên chạy thẳng file `.java`, không cần IDE hay bước biên dịch riêng. Mở terminal trong thư mục chứa file rồi gõ:

```bash
java Ngay1_BienVaKieu.java
```

Chuỗi in ra màn hình trong các file được viết **không dấu** để tránh lỗi font trên terminal Windows. Chú thích (comment) vẫn viết có dấu.

## Lịch học

| Buổi | Chủ đề | Ví dụ (thư mục `vidu`) | Bài tập (thư mục `baitap`) |
|------|--------|------------------------|----------------------------|
| 1 | Biến, kiểu dữ liệu, toán tử, String | `Ngay1_BienVaKieu.java` | `BaiTap1_QuangDuong.java` |
| 2 | Điều kiện, vòng lặp, mảng | `Ngay2_DieuKienVongLap.java` | `BaiTap2_ThongKeTocDo.java` |
| 3 | Class, object, constructor, đóng gói | `Ngay3_ClassVaObject.java` | `BaiTap3_BinhNhienLieu.java` |
| 4 | Kế thừa, đa hình, abstract class | `Ngay4_KeThua.java` | `BaiTap4_BaiGuiXe.java` |
| 5 | Interface, callback/listener | `Ngay5_Interface.java` | `BaiTap5_BangDieuKhien.java` |

Mỗi buổi: đọc phần lý thuyết bên dưới → chạy file ví dụ → **sửa thử vài chỗ trong ví dụ xem kết quả đổi thế nào** → làm bài tập.

---

## Buổi 1 – Biến, kiểu dữ liệu, toán tử, String

**Kiểu nguyên thuỷ hay dùng**

| Kiểu | Dùng cho | Ví dụ |
|------|----------|-------|
| `int` | số nguyên | `int tocDo = 60;` |
| `long` | số nguyên lớn | `long odo = 123_456L;` |
| `double` | số thực | `double nhienLieu = 42.5;` |
| `boolean` | đúng/sai | `boolean dangNoMay = true;` |
| `char` | một ký tự | `char so = 'D';` |

`String` là kiểu đối tượng (không phải nguyên thuỷ), viết trong dấu nháy kép.

**Những điểm dễ sai**

- `int / int` cho kết quả `int`: `7 / 2` bằng `3`, không phải `3.5`. Muốn ra số thực thì một vế phải là `double`: `7 / 2.0`.
- So sánh `String` phải dùng `.equals()`, **không dùng `==`**. `==` so sánh địa chỉ đối tượng chứ không so nội dung.
- `final` nghĩa là không được gán lại: `final int SO_BANH = 4;`
- Ép kiểu từ lớn về nhỏ phải viết rõ và sẽ mất phần thập phân: `int x = (int) 9.99;` cho `9`.

## Buổi 2 – Điều kiện, vòng lặp, mảng

- `if / else if / else` cho điều kiện theo khoảng giá trị.
- `switch` cho trường hợp theo giá trị rời rạc. Cú pháp mũi tên `case 'D' -> ...` gọn hơn và không cần `break`.
- `for` khi biết số lần lặp, `while` khi lặp đến lúc điều kiện sai, `for-each` (`for (int v : mang)`) để duyệt hết mảng.
- Mảng có kích thước cố định, chỉ số bắt đầu từ `0`, phần tử cuối là `mang[mang.length - 1]`. Truy cập ngoài phạm vi gây lỗi `ArrayIndexOutOfBoundsException`.

## Buổi 3 – Class, object, constructor, đóng gói

- **Class** là bản thiết kế, **object** là một thực thể tạo từ bản thiết kế bằng `new`.
- **Constructor** là hàm chạy khi tạo object, cùng tên với class, không có kiểu trả về.
- `this` chỉ object hiện tại; dùng `this.tocDo = tocDo` khi tên tham số trùng tên thuộc tính.
- **Đóng gói:** để thuộc tính là `private`, cho bên ngoài thao tác qua phương thức `public`. Nhờ vậy class tự kiểm tra dữ liệu hợp lệ (ví dụ không cho tốc độ âm).
- `static` thuộc về class chứ không thuộc về từng object (ví dụ bộ đếm tổng số xe đã tạo).

## Buổi 4 – Kế thừa, đa hình, abstract class

- `class XeTai extends PhuongTien`: `XeTai` có sẵn mọi thứ của `PhuongTien` và thêm phần riêng.
- `super(...)` gọi constructor của class cha; phải là dòng đầu tiên trong constructor con.
- `@Override` ghi đè phương thức của cha. Luôn viết annotation này để trình biên dịch báo lỗi khi bạn gõ sai tên.
- **Đa hình:** biến kiểu cha giữ được object kiểu con, và lời gọi phương thức chạy bản của kiểu con thật sự:
  `PhuongTien x = new XeTai(...); x.tinhPhi();` chạy `tinhPhi` của `XeTai`.
- `abstract class` không tạo object trực tiếp được; phương thức `abstract` bắt class con phải tự cài đặt.

## Buổi 5 – Interface, callback/listener

- `interface` mô tả **khả năng** ("sạc được", "đọc được giá trị"), không chứa trạng thái. Một class `implements` được nhiều interface nhưng chỉ `extends` được một class.
- Chọn thế nào: quan hệ "là một loại" và có chung dữ liệu → abstract class; chỉ chung "làm được việc gì" → interface.
- **Listener (callback):** object A giữ một interface, khi có sự kiện thì gọi phương thức của interface đó; ai muốn nghe thì đưa cài đặt của mình vào. Android dùng mẫu này ở khắp nơi (bấm nút, dữ liệu xe thay đổi…), nên hãy hiểu kỹ ví dụ buổi 5.
- Interface chỉ có một phương thức có thể viết ngắn bằng **lambda**: `tocDo -> System.out.println(tocDo)`.

---

## Tự kiểm tra cuối tuần

Trả lời được không cần tra tài liệu thì bạn đã sẵn sàng sang Tuần 2:

1. `10 / 4` và `10 / 4.0` khác nhau thế nào? Vì sao?
2. Vì sao so sánh hai `String` bằng `==` có thể sai?
3. `private` giúp gì so với để thuộc tính `public`?
4. Thuộc tính `static` khác thuộc tính thường ở điểm nào?
5. `PhuongTien x = new XeTai();` rồi gọi `x.tinhPhi()` thì bản nào chạy? Khái niệm này tên gì?
6. Khi nào chọn abstract class, khi nào chọn interface?
7. Listener hoạt động thế nào? Kể một ví dụ ngoài bài học.

## Làm bài tập

- Mỗi file trong `baitap` có sẵn khung và các chỗ `// TODO`. Phần `main` đã viết sẵn lời gọi kiểm tra, kèm **kết quả mong đợi** ở cuối file để bạn so.
- File chạy được ngay cả khi chưa làm (in ra giá trị sai như `0`), nên cứ làm từng TODO rồi chạy lại.
- Không có lời giải kèm theo. Làm xong, bạn nhờ tôi xem code để nhận xét sẽ học được nhiều hơn là chép đáp án.
