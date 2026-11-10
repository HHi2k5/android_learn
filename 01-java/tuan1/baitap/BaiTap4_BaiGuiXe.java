// Bài tập 4 (Buổi 4): bãi gửi xe dùng kế thừa và đa hình
// Chạy: java BaiTap4_BaiGuiXe.java
//
// Phần main đã viết sẵn, KHÔNG sửa. Hãy hoàn thành các class bên dưới.
//
// Bảng giá (nghìn đồng):
//   XeMay : 5 cho mỗi lượt gửi, không tính theo giờ
//   OTo   : 25 cho 2 giờ đầu, mỗi giờ tiếp theo thêm 10
//   XeBuyt: tính như OTo rồi nhân 2; nếu trên 30 chỗ thì cộng thêm 50
public class BaiTap4_BaiGuiXe {
    public static void main(String[] args) {
        PhuongTienGui[] bai = {
            new XeMay("29X1-123.45"),
            new OTo("30A-111.11"),
            new XeBuyt("29B-222.22", 45),
            new XeBuyt("29B-333.33", 16)
        };
        int[] soGio = {5, 1, 3, 4};

        int tong = 0;
        for (int i = 0; i < bai.length; i++) {
            int phi = bai[i].tinhPhi(soGio[i]);
            tong += phi;
            System.out.println(bai[i].moTa() + " gui " + soGio[i] + " gio: " + phi + " nghin");
        }
        System.out.println("Tong thu: " + tong + " nghin");
    }
}

abstract class PhuongTienGui {
    private final String bienSo;

    PhuongTienGui(String bienSo) {
        this.bienSo = bienSo;
    }

    String getBienSo() {
        return bienSo;
    }

    // Tên loại xe, ví dụ "Xe may"
    abstract String loai();

    // Phí gửi theo số giờ
    abstract int tinhPhi(int soGio);

    // Dùng chung cho mọi class con, không cần ghi đè
    String moTa() {
        return loai() + " " + bienSo;
    }
}

// TODO 1: hoàn thành XeMay
class XeMay extends PhuongTienGui {
    XeMay(String bienSo) {
        super(bienSo);
    }

    @Override
    String loai() {
        return "";
    }

    @Override
    int tinhPhi(int soGio) {
        return 0;
    }
}

// TODO 2: hoàn thành OTo
class OTo extends PhuongTienGui {
    OTo(String bienSo) {
        super(bienSo);
    }

    @Override
    String loai() {
        return "";
    }

    @Override
    int tinhPhi(int soGio) {
        return 0;
    }
}

// TODO 3: hoàn thành XeBuyt. XeBuyt kế thừa OTo và dùng lại cách tính của OTo qua super.tinhPhi(...)
class XeBuyt extends OTo {
    // TODO: thêm thuộc tính soCho và gán trong constructor

    XeBuyt(String bienSo, int soCho) {
        super(bienSo);
    }

    // TODO: ghi đè loai() trả về "Xe buyt <soCho> cho"

    // TODO: ghi đè tinhPhi(int soGio)
}

/* KẾT QUẢ MONG ĐỢI
Xe may 29X1-123.45 gui 5 gio: 5 nghin
O to 30A-111.11 gui 1 gio: 25 nghin
Xe buyt 45 cho 29B-222.22 gui 3 gio: 120 nghin
Xe buyt 16 cho 29B-333.33 gui 4 gio: 90 nghin
Tong thu: 240 nghin
*/
