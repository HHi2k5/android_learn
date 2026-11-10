// Buổi 3: class, object, constructor, đóng gói, static
// Chạy: java Ngay3_ClassVaObject.java
public class Ngay3_ClassVaObject {
    public static void main(String[] args) {
        XeHoi xe1 = new XeHoi("30A-111.11", 180);
        XeHoi xe2 = new XeHoi("51K-222.22", 220);

        xe1.tangToc(50);
        xe1.tangToc(200);          // vượt tốc độ tối đa -> bị chặn ở 180
        xe2.tangToc(-10);          // giá trị không hợp lệ -> bị từ chối
        xe2.tangToc(90);
        xe2.phanh();

        // xe1.tocDo = 999;        // lỗi biên dịch: tocDo là private
        System.out.println(xe1.moTa());
        System.out.println(xe2.moTa());
        System.out.println("Toc do xe1 qua getter: " + xe1.getTocDo());

        // static: gọi qua tên class, dùng chung cho mọi object
        System.out.println("Tong so xe da tao: " + XeHoi.getSoXeDaTao());
    }
}

class XeHoi {
    // Thuộc tính private: bên ngoài không sửa trực tiếp được
    private final String bienSo;       // final: gán một lần trong constructor
    private final int tocDoToiDa;
    private int tocDo;                 // mặc định 0

    // static: thuộc về class, không thuộc về từng xe
    private static int soXeDaTao = 0;

    // Constructor: cùng tên class, không có kiểu trả về
    XeHoi(String bienSo, int tocDoToiDa) {
        this.bienSo = bienSo;          // this.bienSo là thuộc tính, bienSo là tham số
        this.tocDoToiDa = tocDoToiDa;
        soXeDaTao++;
    }

    // Phương thức kiểm tra dữ liệu trước khi thay đổi trạng thái
    void tangToc(int luong) {
        if (luong <= 0) {
            System.out.println(bienSo + ": luong tang phai lon hon 0");
            return;
        }
        tocDo = Math.min(tocDo + luong, tocDoToiDa);
    }

    void phanh() {
        tocDo = 0;
    }

    // Getter: cho đọc nhưng không cho ghi
    int getTocDo() {
        return tocDo;
    }

    String moTa() {
        return "Xe " + bienSo + " dang chay " + tocDo + "/" + tocDoToiDa + " km/h";
    }

    static int getSoXeDaTao() {
        return soXeDaTao;
    }
}
