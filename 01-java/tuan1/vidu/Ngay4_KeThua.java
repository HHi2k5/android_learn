// Buổi 4: kế thừa, đa hình, abstract class
// Chạy: java Ngay4_KeThua.java
public class Ngay4_KeThua {
    public static void main(String[] args) {
        // PhuongTien p = new PhuongTien("x");   // lỗi: abstract class không tạo object được

        // Đa hình: mảng kiểu cha chứa object của nhiều kiểu con
        PhuongTien[] danhSach = {
            new XeCon("30A-111.11", 5),
            new XeTai("29C-222.22", 8.5),
            new XeDien("30E-333.33", 5, 75)
        };

        for (PhuongTien p : danhSach) {
            // Cùng một lời gọi, mỗi object chạy bản tinhPhiDuongBo() của riêng nó
            System.out.println(p.moTa() + " | phi duong bo: " + p.tinhPhiDuongBo() + " nghin/thang");
        }

        // instanceof: kiểm tra kiểu thật của object
        for (PhuongTien p : danhSach) {
            if (p instanceof XeDien xeDien) {
                System.out.println(xeDien.getBienSo() + " co pin " + xeDien.getDungLuongPin() + " kWh");
            }
        }
    }
}

abstract class PhuongTien {
    private final String bienSo;

    PhuongTien(String bienSo) {
        this.bienSo = bienSo;
    }

    String getBienSo() {
        return bienSo;
    }

    // Phương thức thường: class con dùng lại hoặc ghi đè
    String moTa() {
        return "Phuong tien " + bienSo;
    }

    // Phương thức abstract: không có thân, class con bắt buộc cài đặt
    abstract int tinhPhiDuongBo();
}

class XeCon extends PhuongTien {
    private final int soCho;

    XeCon(String bienSo, int soCho) {
        super(bienSo);                 // gọi constructor của cha, phải ở dòng đầu
        this.soCho = soCho;
    }

    @Override
    String moTa() {
        return "Xe con " + getBienSo() + " (" + soCho + " cho)";
    }

    @Override
    int tinhPhiDuongBo() {
        return 130;
    }
}

class XeTai extends PhuongTien {
    private final double taiTrong;     // tấn

    XeTai(String bienSo, double taiTrong) {
        super(bienSo);
        this.taiTrong = taiTrong;
    }

    @Override
    String moTa() {
        return "Xe tai " + getBienSo() + " (" + taiTrong + " tan)";
    }

    @Override
    int tinhPhiDuongBo() {
        return taiTrong < 4 ? 180 : 390;
    }
}

// Kế thừa nhiều tầng: XeDien là một XeCon, XeCon là một PhuongTien
class XeDien extends XeCon {
    private final int dungLuongPin;    // kWh

    XeDien(String bienSo, int soCho, int dungLuongPin) {
        super(bienSo, soCho);
        this.dungLuongPin = dungLuongPin;
    }

    int getDungLuongPin() {
        return dungLuongPin;
    }

    @Override
    String moTa() {
        return super.moTa() + " [dien]";   // super.moTa(): dùng lại bản của XeCon
    }
    // Không ghi đè tinhPhiDuongBo() -> dùng bản của XeCon
}
