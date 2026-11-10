// Buổi 1: biến, kiểu dữ liệu, toán tử, String
// Chạy: java Ngay1_BienVaKieu.java
public class Ngay1_BienVaKieu {
    public static void main(String[] args) {
        // --- Kiểu nguyên thuỷ ---
        int tocDo = 60;               // km/h
        long odo = 123_456L;          // dấu _ chỉ để dễ đọc
        double nhienLieu = 42.5;      // lít
        boolean dangNoMay = true;
        char so = 'D';                // một ký tự, dùng nháy đơn

        // --- String là đối tượng ---
        String bienSo = "30A-123.45";

        System.out.println("Bien so: " + bienSo);
        System.out.println("Toc do: " + tocDo + " km/h, so: " + so);
        System.out.println("Odo: " + odo + " km, no may: " + dangNoMay);

        // --- Toán tử ---
        int quangDuong = 250;         // km
        int thoiGian = 4;             // giờ
        System.out.println("Chia nguyen: " + quangDuong / thoiGian);        // 62 (mất phần lẻ)
        System.out.println("Chia thuc:   " + quangDuong / (double) thoiGian); // 62.5
        System.out.println("Chia du:     " + quangDuong % thoiGian);        // 2

        tocDo += 20;                  // giống tocDo = tocDo + 20
        tocDo++;                      // tăng 1
        System.out.println("Toc do moi: " + tocDo);

        // --- So sánh và logic ---
        boolean quaTocDo = tocDo > 80;
        boolean canhBao = quaTocDo && dangNoMay;   // && là VÀ, || là HOẶC, ! là PHỦ ĐỊNH
        System.out.println("Qua toc do: " + quaTocDo + ", canh bao: " + canhBao);

        // --- Ép kiểu ---
        int lamTron = (int) nhienLieu;             // 42, cắt bỏ phần thập phân
        System.out.println("Nhien lieu (int): " + lamTron);

        // --- Hằng số ---
        final int SO_BANH = 4;
        // SO_BANH = 6;   // bỏ comment dòng này sẽ lỗi biên dịch
        System.out.println("So banh: " + SO_BANH);

        // --- Một số phương thức của String ---
        System.out.println("Do dai: " + bienSo.length());
        System.out.println("Ma tinh: " + bienSo.substring(0, 2));
        System.out.println("Chu thuong: " + bienSo.toLowerCase());
        System.out.println("Co chua 'A': " + bienSo.contains("A"));

        // --- So sánh String: dùng equals, KHÔNG dùng == ---
        String a = "30A";
        String b = new String("30A");
        System.out.println("a == b:      " + (a == b));       // false: hai đối tượng khác nhau
        System.out.println("a.equals(b): " + a.equals(b));    // true: nội dung giống nhau

        // --- Định dạng số ---
        double tieuThu = 7.3456;
        System.out.printf("Tieu thu: %.1f l/100km%n", tieuThu);  // %.1f = 1 chữ số thập phân
    }
}
