// Bài tập 2 (Buổi 2): thống kê tốc độ của một chuyến đi
// Chạy: java BaiTap2_ThongKeTocDo.java
//
// Mảng tocDo chứa tốc độ (km/h) đo mỗi phút. Hoàn thành các TODO.
public class BaiTap2_ThongKeTocDo {
    public static void main(String[] args) {
        int[] tocDo = {0, 25, 48, 62, 85, 92, 78, 101, 66, 40, 0, 0};
        final int GIOI_HAN = 80;

        // TODO 1: tìm tốc độ lớn nhất
        int lonNhat = 0;

        // TODO 2: tính tốc độ trung bình (kết quả là số thực)
        double trungBinh = 0;

        // TODO 3: đếm số phút vượt quá GIOI_HAN
        int soPhutVuot = 0;

        // TODO 4: tìm vị trí (chỉ số) đầu tiên vượt GIOI_HAN; nếu không có thì là -1
        //         Gợi ý: dùng break khi tìm thấy
        int viTriVuotDauTien = -1;

        // TODO 5: đếm số phút xe đứng yên (tốc độ bằng 0)
        int soPhutDung = 0;

        System.out.println("Lon nhat: " + lonNhat + " km/h");
        System.out.printf("Trung binh: %.2f km/h%n", trungBinh);
        System.out.println("So phut vuot " + GIOI_HAN + ": " + soPhutVuot);
        System.out.println("Vuot lan dau o phut thu: " + viTriVuotDauTien);
        System.out.println("So phut dung: " + soPhutDung);

        // TODO 6: với mỗi tốc độ trong mảng, in ra một dòng "<toc do> -> <muc>"
        //         muc là: "Dung" nếu bằng 0, "Cham" nếu dưới 40,
        //         "Binh thuong" nếu từ 40 đến GIOI_HAN, "Nhanh" nếu trên GIOI_HAN
        //         Kết quả mong đợi chỉ liệt kê 4 dòng đầu.

        // TODO 7: viết thân hàm tenCheDoLai bên dưới bằng switch
        System.out.println("Che do E: " + tenCheDoLai('E'));
        System.out.println("Che do S: " + tenCheDoLai('S'));
        System.out.println("Che do X: " + tenCheDoLai('X'));
    }

    // 'E' -> "Tiet kiem", 'C' -> "Thoai mai", 'S' -> "The thao", còn lại -> "Khong ro"
    static String tenCheDoLai(char kyHieu) {
        return "";
    }
}

/* KẾT QUẢ MONG ĐỢI
Lon nhat: 101 km/h
Trung binh: 49.75 km/h
So phut vuot 80: 3
Vuot lan dau o phut thu: 4
So phut dung: 3
0 -> Dung
25 -> Cham
48 -> Binh thuong
62 -> Binh thuong
... (tiếp tục đến hết mảng)
Che do E: Tiet kiem
Che do S: The thao
Che do X: Khong ro
*/
