// Bài tập 1 (Buổi 1): tính quãng đường còn đi được
// Chạy: java BaiTap1_QuangDuong.java
//
// Xe có bình 50 lít, hiện còn 18.5 lít. Xe vừa đi 420 km hết 31.5 lít.
// Hãy tính và in ra các giá trị theo các TODO bên dưới.
public class BaiTap1_QuangDuong {
    public static void main(String[] args) {
        final double DUNG_TICH_BINH = 50.0;   // lít
        double nhienLieuConLai = 18.5;        // lít
        int quangDuongDaDi = 420;             // km
        double nhienLieuDaDung = 31.5;        // lít
        String bienSo = "30a-567.89";

        // TODO 1: tính mức tiêu thụ (lít / 100 km)
        double tieuThu = 0;

        // TODO 2: tính quãng đường còn đi được (km) với lượng nhiên liệu còn lại
        double conDiDuoc = 0;

        // TODO 3: lấy phần nguyên của conDiDuoc (ép kiểu về int)
        int conDiDuocLamTron = 0;

        // TODO 4: tính phần trăm nhiên liệu còn lại trong bình
        double phanTram = 0;

        // TODO 5: canhBao là true khi phần trăm dưới 20 HOẶC quãng đường còn lại dưới 100 km
        boolean canhBao = false;

        // TODO 6: chuyển biển số thành chữ in hoa
        String bienSoChuan = bienSo;

        // TODO 7: lấy 2 ký tự đầu của biển số (mã tỉnh)
        String maTinh = "";

        // TODO 8: laHaNoi là true khi mã tỉnh bằng "30" (nhớ cách so sánh String)
        boolean laHaNoi = false;

        System.out.println("Bien so: " + bienSoChuan + ", ma tinh: " + maTinh + ", Ha Noi: " + laHaNoi);
        System.out.printf("Tieu thu: %.1f l/100km%n", tieuThu);
        System.out.printf("Con di duoc: %.1f km (lam tron: %d km)%n", conDiDuoc, conDiDuocLamTron);
        System.out.printf("Nhien lieu: %.0f%%, canh bao: %b%n", phanTram, canhBao);
    }
}

/* KẾT QUẢ MONG ĐỢI
Bien so: 30A-567.89, ma tinh: 30, Ha Noi: true
Tieu thu: 7.5 l/100km
Con di duoc: 246.7 km (lam tron: 246 km)
Nhien lieu: 37%, canh bao: false
*/
