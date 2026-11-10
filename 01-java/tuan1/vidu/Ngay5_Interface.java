// Buổi 5: interface và mẫu listener (callback)
// Chạy: java Ngay5_Interface.java
public class Ngay5_Interface {
    public static void main(String[] args) {
        // --- Interface mô tả khả năng ---
        CoTheSac[] thietBi = { new XeDienMini("VF-01"), new DienThoai() };
        for (CoTheSac t : thietBi) {
            t.sac(80);                 // không cần biết đó là xe hay điện thoại
        }

        // --- Listener: đăng ký nghe sự kiện tốc độ thay đổi ---
        CamBienTocDo camBien = new CamBienTocDo();

        // Cách 1: class có tên cài đặt interface
        camBien.setListener(new ManHinhTocDo());
        camBien.capNhat(40);

        // Cách 2: anonymous class (class không tên, viết tại chỗ)
        camBien.setListener(new TocDoListener() {
            @Override
            public void onTocDoThayDoi(int tocDo) {
                if (tocDo > 80) {
                    System.out.println("[Canh bao] Vuot 80 km/h: " + tocDo);
                }
            }
        });
        camBien.capNhat(70);           // không in gì
        camBien.capNhat(95);           // in cảnh báo

        // Cách 3: lambda, viết gọn cho interface chỉ có một phương thức
        camBien.setListener(tocDo -> System.out.println("[Lambda] Toc do = " + tocDo));
        camBien.capNhat(60);
    }
}

// Interface: chỉ khai báo "làm được gì", không nói "làm thế nào"
interface CoTheSac {
    void sac(int phanTram);
}

class XeDienMini implements CoTheSac {
    private final String ten;

    XeDienMini(String ten) {
        this.ten = ten;
    }

    @Override
    public void sac(int phanTram) {    // phương thức của interface luôn là public
        System.out.println("Xe " + ten + " sac den " + phanTram + "% bang tram sac nhanh");
    }
}

class DienThoai implements CoTheSac {
    @Override
    public void sac(int phanTram) {
        System.out.println("Dien thoai sac den " + phanTram + "% bang cong USB");
    }
}

// Interface listener: ai muốn nghe sự kiện thì cài đặt interface này
interface TocDoListener {
    void onTocDoThayDoi(int tocDo);
}

// Nguồn phát sự kiện: không biết ai đang nghe, chỉ biết gọi listener
class CamBienTocDo {
    private TocDoListener listener;

    void setListener(TocDoListener listener) {
        this.listener = listener;
    }

    void capNhat(int tocDoMoi) {
        if (listener != null) {
            listener.onTocDoThayDoi(tocDoMoi);
        }
    }
}

class ManHinhTocDo implements TocDoListener {
    @Override
    public void onTocDoThayDoi(int tocDo) {
        System.out.println("[Man hinh] Hien thi " + tocDo + " km/h");
    }
}
