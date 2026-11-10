// Buổi 2: điều kiện, vòng lặp, mảng
// Chạy: java Ngay2_DieuKienVongLap.java
public class Ngay2_DieuKienVongLap {
    public static void main(String[] args) {
        // --- if / else if / else ---
        int tocDo = 95;
        if (tocDo > 120) {
            System.out.println("Nguy hiem!");
        } else if (tocDo > 80) {
            System.out.println("Vuot toc do cho phep");
        } else {
            System.out.println("Toc do an toan");
        }

        // --- switch kiểu mũi tên, trả về giá trị ---
        char so = 'R';
        String tenSo = switch (so) {
            case 'P' -> "Do xe";
            case 'R' -> "Lui";
            case 'N' -> "So mo";
            case 'D' -> "Tien";
            default -> "Khong hop le";
        };
        System.out.println("So " + so + ": " + tenSo);

        // --- Toán tử 3 ngôi: điều kiện ? giá trị nếu đúng : giá trị nếu sai ---
        String camLui = (so == 'R') ? "BAT" : "TAT";
        System.out.println("Camera lui: " + camLui);

        // --- for: biết trước số lần lặp ---
        for (int i = 1; i <= 3; i++) {
            System.out.println("Kiem tra lop so " + i);
        }

        // --- while: lặp đến khi điều kiện sai ---
        int pin = 70;
        int soLanSac = 0;
        while (pin < 100) {
            pin += 10;
            soLanSac++;
        }
        System.out.println("Sac " + soLanSac + " lan thi day, pin = " + pin + "%");

        // --- Mảng ---
        int[] apSuatLop = {32, 31, 28, 33};          // 4 lốp, đơn vị psi
        System.out.println("So lop: " + apSuatLop.length);
        System.out.println("Lop dau: " + apSuatLop[0] + ", lop cuoi: " + apSuatLop[apSuatLop.length - 1]);

        // Duyệt bằng chỉ số khi cần biết vị trí
        for (int i = 0; i < apSuatLop.length; i++) {
            if (apSuatLop[i] < 30) {
                System.out.println("Lop " + (i + 1) + " non hoi: " + apSuatLop[i] + " psi");
            }
        }

        // Duyệt bằng for-each khi chỉ cần giá trị
        int tong = 0;
        for (int ap : apSuatLop) {
            tong += ap;
        }
        System.out.println("Ap suat trung binh: " + (double) tong / apSuatLop.length);

        // Tạo mảng rỗng rồi gán sau
        double[] nhietDo = new double[3];            // mặc định toàn 0.0
        nhietDo[0] = 88.5;
        System.out.println("Nhiet do[0] = " + nhietDo[0] + ", nhiet do[1] = " + nhietDo[1]);

        // break thoát vòng lặp, continue bỏ qua lượt hiện tại
        for (int v = 0; v <= 100; v += 20) {
            if (v == 40) continue;
            if (v == 80) break;
            System.out.println("v = " + v);
        }
    }
}
