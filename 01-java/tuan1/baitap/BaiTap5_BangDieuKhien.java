// Bài tập 5 (Buổi 5): bảng điều khiển đọc cảm biến và phát cảnh báo qua listener
// Chạy: java BaiTap5_BangDieuKhien.java
//
// Đây là bài tổng hợp cả tuần. Phần main đã viết sẵn, KHÔNG sửa.
// Mô hình này gần với cách app ô tô thật làm việc: đọc giá trị từ xe rồi báo cho giao diện.
public class BaiTap5_BangDieuKhien {
    public static void main(String[] args) {
        CamBienNhietDo nhietDo = new CamBienNhietDo(90);
        CamBienApSuatLop apSuat = new CamBienApSuatLop(32);

        BangDieuKhien bang = new BangDieuKhien(new CamBien[] { nhietDo, apSuat });

        // Đăng ký listener bằng lambda
        bang.setListener((tenCamBien, giaTri) ->
                System.out.println("  >> CANH BAO: " + tenCamBien + " = " + giaTri));

        System.out.println("Lan 1:");
        bang.kiemTra();                // mọi thứ bình thường, không có cảnh báo

        nhietDo.setGiaTri(118);        // quá nóng
        apSuat.setGiaTri(24);          // lốp non
        System.out.println("Lan 2:");
        bang.kiemTra();

        bang.setListener(null);        // bỏ listener, không được lỗi
        System.out.println("Lan 3:");
        bang.kiemTra();
    }
}

// TODO 1: khai báo interface CamBien có 3 phương thức:
//   String ten();
//   double docGiaTri();
//   boolean batThuong();
interface CamBien {
}

// TODO 2: khai báo interface CanhBaoListener có 1 phương thức:
//   void onCanhBao(String tenCamBien, double giaTri);
interface CanhBaoListener {
    void onCanhBao(String tenCamBien, double giaTri);   // đã viết sẵn để main biên dịch được
}

// TODO 3: cho CamBienNhietDo implements CamBien
//   ten() trả về "Nhiet do dong co"; bất thường khi giá trị lớn hơn 110
class CamBienNhietDo {
    private double giaTri;

    CamBienNhietDo(double giaTri) {
        this.giaTri = giaTri;
    }

    void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}

// TODO 4: cho CamBienApSuatLop implements CamBien
//   ten() trả về "Ap suat lop"; bất thường khi giá trị nhỏ hơn 28 hoặc lớn hơn 36
class CamBienApSuatLop {
    private double giaTri;

    CamBienApSuatLop(double giaTri) {
        this.giaTri = giaTri;
    }

    void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}

class BangDieuKhien {
    private final CamBien[] danhSach;
    private CanhBaoListener listener;

    BangDieuKhien(CamBien[] danhSach) {
        this.danhSach = danhSach;
    }

    void setListener(CanhBaoListener listener) {
        this.listener = listener;
    }

    // TODO 5: duyệt mọi cảm biến:
    //   - in ra dòng "  <ten>: <gia tri>"
    //   - nếu cảm biến bất thường VÀ có listener thì gọi listener.onCanhBao(...)
    void kiemTra() {
    }
}

/* KẾT QUẢ MONG ĐỢI
Lan 1:
  Nhiet do dong co: 90.0
  Ap suat lop: 32.0
Lan 2:
  Nhiet do dong co: 118.0
  >> CANH BAO: Nhiet do dong co = 118.0
  Ap suat lop: 24.0
  >> CANH BAO: Ap suat lop = 24.0
Lan 3:
  Nhiet do dong co: 118.0
  Ap suat lop: 24.0
*/
