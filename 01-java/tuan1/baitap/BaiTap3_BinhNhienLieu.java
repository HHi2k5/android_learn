// Bài tập 3 (Buổi 3): viết class BinhNhienLieu có đóng gói
// Chạy: java BaiTap3_BinhNhienLieu.java
//
// Phần main đã viết sẵn, KHÔNG sửa. Hãy hoàn thành class BinhNhienLieu bên dưới.
public class BaiTap3_BinhNhienLieu {
    public static void main(String[] args) {
        BinhNhienLieu binh = new BinhNhienLieu(50, 20);
        System.out.println(binh.moTa());

        System.out.println("Do 15 lit, thuc te do duoc: " + binh.doThem(15));
        System.out.println("Do 40 lit, thuc te do duoc: " + binh.doThem(40));   // chỉ còn chỗ cho 15
        System.out.println("Do -5 lit, thuc te do duoc: " + binh.doThem(-5));   // không hợp lệ -> 0
        System.out.println(binh.moTa());

        System.out.println("Tieu thu 42.5 lit: " + binh.tieuThu(42.5));         // true
        System.out.println("Tieu thu 10 lit: " + binh.tieuThu(10));             // false, không đủ
        System.out.println(binh.moTa());
        System.out.println("Sap het: " + binh.sapHet());

        BinhNhienLieu binh2 = new BinhNhienLieu(60, 100);                       // vượt dung tích -> đầy bình
        System.out.println(binh2.moTa());
        System.out.println("So binh da tao: " + BinhNhienLieu.getSoBinhDaTao());
    }
}

class BinhNhienLieu {
    // TODO 1: khai báo thuộc tính private:
    //   - dungTich (double, không đổi sau khi tạo)
    //   - mucHienTai (double)
    //   - soBinhDaTao (int, static)

    // TODO 2: constructor nhận dungTich và mucBanDau.
    //   Nếu mucBanDau lớn hơn dungTich thì lấy bằng dungTich. Tăng soBinhDaTao.
    BinhNhienLieu(double dungTich, double mucBanDau) {
    }

    // TODO 3: đổ thêm nhiên liệu. Trả về số lít THỰC TẾ đổ được.
    //   - soLit <= 0: không đổ, trả về 0
    //   - vượt dung tích: chỉ đổ đến đầy
    double doThem(double soLit) {
        return 0;
    }

    // TODO 4: tiêu thụ nhiên liệu. Đủ thì trừ đi và trả về true; không đủ thì giữ nguyên, trả về false.
    boolean tieuThu(double soLit) {
        return false;
    }

    // TODO 5: phần trăm nhiên liệu hiện tại (0 đến 100)
    double phanTram() {
        return 0;
    }

    // TODO 6: true khi phần trăm dưới 15
    boolean sapHet() {
        return false;
    }

    // TODO 7: trả về chuỗi dạng "Binh 50.0 lit, con 20.0 lit (40%)"
    //   Gợi ý: String.format("Binh %.1f lit, con %.1f lit (%.0f%%)", ...)
    String moTa() {
        return "";
    }

    // TODO 8: trả về soBinhDaTao
    static int getSoBinhDaTao() {
        return 0;
    }
}

/* KẾT QUẢ MONG ĐỢI
Binh 50.0 lit, con 20.0 lit (40%)
Do 15 lit, thuc te do duoc: 15.0
Do 40 lit, thuc te do duoc: 15.0
Do -5 lit, thuc te do duoc: 0.0
Binh 50.0 lit, con 50.0 lit (100%)
Tieu thu 42.5 lit: true
Tieu thu 10 lit: false
Binh 50.0 lit, con 7.5 lit (15%)
Sap het: false
Binh 60.0 lit, con 60.0 lit (100%)
So binh da tao: 2
*/
