# Lộ trình học Android → Automotive

**Mục tiêu:** Java → Kotlin → Android nền tảng → Jetpack Compose → làm project app trên ô tô (Android Auto / Android Automotive OS).

**Giả định:** đã biết lập trình cơ bản (biến, vòng lặp, hàm), học ~10–15 giờ/tuần. Tổng ~30 tuần (≈ 7 tháng). Học nhiều giờ hơn thì rút ngắn tương ứng.

**Nguyên tắc học:** 30% đọc/xem – 70% gõ code. Mỗi giai đoạn kết thúc bằng một project nhỏ, đẩy lên GitHub.

---

## Tổng quan

| # | Giai đoạn | Thời lượng | Sản phẩm cuối |
|---|-----------|-----------|----------------|
| 0 | Cài đặt môi trường | 2–3 ngày | Chạy được "Hello World" trên emulator |
| 1 | Java nền tảng | 3 tuần | App console quản lý (OOP + collections) |
| 2 | Kotlin | 3 tuần | Viết lại project Java bằng Kotlin + coroutines |
| 3 | Android nền tảng | 4 tuần | App nhiều màn hình + Service |
| 4 | Jetpack Compose | 4 tuần | App Ghi chú UI hoàn chỉnh |
| 5 | Kiến trúc & dữ liệu | 4 tuần | App Thời tiết (MVVM, Room, Retrofit, Hilt) |
| 6 | Cầu nối sang ô tô | 3 tuần | App nghe nhạc chạy nền (Media3) |
| 7 | Automotive | 6–8 tuần | 3 project ô tô (xem bên dưới) |
| 8 | Nâng cao (tuỳ chọn) | mở | Build AOSP, VHAL, system app |

---

## Giai đoạn 0 — Cài đặt môi trường (2–3 ngày)

- [ ] Cài **Android Studio** bản stable mới nhất (đã kèm JDK)
- [ ] Tạo emulator điện thoại (Pixel, API mới nhất), chạy project mẫu "Empty Activity"
- [ ] Cài **Git**, tạo tài khoản GitHub, học: `clone`, `add`, `commit`, `push`, `branch`
- [ ] Làm quen Android Studio: Logcat, Debugger (breakpoint), Gradle sync

> Máy nên có RAM ≥ 16 GB, SSD trống ≥ 50 GB. Bật ảo hoá (VT-x/AMD-V) trong BIOS để emulator chạy mượt.

---

## Giai đoạn 1 — Java nền tảng (3 tuần)

Vì sao vẫn học Java: phần lớn mã nguồn hệ thống Android (AOSP), Car Service và rất nhiều code cũ ở các hãng xe viết bằng Java. Làm automotive **bắt buộc đọc được Java**.

**Tuần 1 – Cú pháp & OOP**
- [ ] Kiểu dữ liệu, toán tử, điều kiện, vòng lặp, mảng, String
- [ ] Class, object, constructor, `static`, `final`
- [ ] 4 tính chất OOP: đóng gói, kế thừa, đa hình, trừu tượng
- [ ] `interface` vs `abstract class`

**Tuần 2 – Thư viện chuẩn**
- [ ] Collections: `List`, `Set`, `Map` (ArrayList, HashMap…)
- [ ] Generics, Exception (`try/catch/finally`, checked vs unchecked)
- [ ] Lambda, functional interface, Stream cơ bản
- [ ] Đọc/ghi file

**Tuần 3 – Đa luồng & project**
- [ ] `Thread`, `Runnable`, `synchronized`, `ExecutorService` (hiểu khái niệm, không cần sâu)
- [ ] Inner class, anonymous class, callback/listener pattern (Android dùng rất nhiều)
- [ ] **Project 1:** App console *Quản lý gara xe* — thêm/sửa/xoá/tìm xe, lưu ra file, dùng interface + kế thừa (Car, Truck, ElectricCar…)

---

## Giai đoạn 2 — Kotlin (3 tuần)

**Tuần 4 – Cơ bản**
- [ ] `val/var`, null safety (`?`, `?.`, `?:`, `!!`), string template
- [ ] Hàm, default/named argument, `when`, range
- [ ] Class, `data class`, `object`, `companion object`, `enum`, `sealed class`

**Tuần 5 – Kotlin "đúng chất"**
- [ ] Lambda, higher-order function, extension function
- [ ] Collections API: `map`, `filter`, `groupBy`, `fold`…
- [ ] Scope functions: `let`, `apply`, `run`, `also`, `with`
- [ ] Generics, delegation (`by lazy`), gọi qua lại Java ↔ Kotlin

**Tuần 6 – Coroutines & Flow**
- [ ] `suspend`, `launch`, `async/await`, `Dispatchers`, structured concurrency
- [ ] `Flow`, `StateFlow`, `SharedFlow` — **rất quan trọng** cho Compose và dữ liệu xe (tốc độ, pin… là luồng dữ liệu)
- [ ] **Project 2:** Viết lại Project 1 bằng Kotlin; thêm "giả lập cảm biến" phát tốc độ xe bằng `Flow` mỗi 500 ms

Tài liệu: kotlinlang.org/docs, Kotlin Koans (bài tập tương tác).

---

## Giai đoạn 3 — Android nền tảng (4 tuần)

**Tuần 7 – Activity & UI cơ bản**
- [ ] Cấu trúc project, `AndroidManifest.xml`, Gradle, resources
- [ ] Activity lifecycle (onCreate → onDestroy), xoay màn hình, lưu state
- [ ] View/XML ở mức **đọc hiểu**: ConstraintLayout, RecyclerView (code ô tô cũ còn nhiều View)

**Tuần 8 – Điều hướng & Intent**
- [ ] Intent explicit/implicit, truyền dữ liệu giữa màn hình
- [ ] Fragment (hiểu khái niệm), back stack
- [ ] Runtime permission

**Tuần 9 – 4 thành phần Android** (nền tảng của mọi app ô tô)
- [ ] **Service**: started, bound, foreground service + notification
- [ ] **BroadcastReceiver**, **ContentProvider** (mức khái niệm)
- [ ] Context, Application class, process & vòng đời app

**Tuần 10 – Project**
- [ ] **Project 3:** App *Nhật ký chuyến đi* — danh sách chuyến đi, màn hình chi tiết, một foreground service đếm thời gian chuyến đi đang chạy

---

## Giai đoạn 4 — Jetpack Compose (4 tuần)

**Tuần 11 – Tư duy Compose**
- [ ] `@Composable`, `@Preview`, `Modifier`
- [ ] Layout: `Column`, `Row`, `Box`, `Scaffold`
- [ ] State: `remember`, `mutableStateOf`, recomposition, **state hoisting**

**Tuần 12 – Danh sách & Material 3**
- [ ] `LazyColumn`, `LazyRow`, `LazyVerticalGrid`
- [ ] Material 3: theme, color, typography, dark mode
- [ ] TextField, Button, Card, Dialog, BottomSheet

**Tuần 13 – ViewModel & Navigation**
- [ ] `ViewModel` + `StateFlow` + `collectAsStateWithLifecycle`
- [ ] Unidirectional Data Flow (UI state ↓, event ↑)
- [ ] Navigation Compose, truyền tham số
- [ ] Side effects: `LaunchedEffect`, `rememberCoroutineScope`, `DisposableEffect`

**Tuần 14 – Hoàn thiện**
- [ ] Animation cơ bản, `Canvas` (vẽ đồng hồ tốc độ — sẽ dùng lại ở project ô tô)
- [ ] Responsive layout cho màn hình ngang/rộng (màn hình xe thường ngang, lớn)
- [ ] **Project 4:** App *Ghi chú* bằng Compose — CRUD, tìm kiếm, dark mode, navigation

Tài liệu chính: khoá **Android Basics with Compose** (developer.android.com/courses) — miễn phí, có codelab từng bước.

---

## Giai đoạn 5 — Kiến trúc & dữ liệu (4 tuần)

- [ ] **Tuần 15:** Kiến trúc MVVM + Repository, phân lớp UI / Domain / Data
- [ ] **Tuần 16:** **Room** (database), **DataStore** (cài đặt)
- [ ] **Tuần 17:** **Retrofit** + kotlinx.serialization, xử lý lỗi/loading; **Hilt** (dependency injection)
- [ ] **Tuần 18:** Unit test (JUnit, coroutine test), Compose UI test cơ bản; WorkManager
- [ ] **Project 5:** App *Thời tiết* — gọi API, cache offline bằng Room, MVVM + Hilt, có test cho ViewModel

---

## Giai đoạn 6 — Cầu nối sang ô tô (3 tuần)

Những mảng Android mà app ô tô dùng nhiều nhất:

- [ ] **Tuần 19 – IPC:** Bound Service, **AIDL**, Binder (app ô tô nói chuyện với Car Service qua Binder)
- [ ] **Tuần 20 – Media:** **Media3** (ExoPlayer), `MediaSession`, `MediaLibraryService`, audio focus
- [ ] **Tuần 21 – Vị trí & project:** Location API, bản đồ cơ bản
- [ ] **Project 6:** App *Nghe nhạc* trên điện thoại — phát nền, điều khiển từ notification/lock screen, duyệt thư viện qua `MediaLibraryService` (project này sẽ "lên xe" ở giai đoạn 7)

---

## Giai đoạn 7 — Automotive (6–8 tuần)

### Phải phân biệt rõ 2 nền tảng

| | **Android Auto** | **Android Automotive OS (AAOS)** |
|---|---|---|
| Chạy ở đâu | App chạy trên **điện thoại**, chiếu lên màn hình xe | Hệ điều hành cài **thẳng trên xe** (Volvo, Polestar, GM, Renault…) |
| Test bằng | Desktop Head Unit (DHU) | AAOS emulator trong Android Studio |
| Truy cập dữ liệu xe | Hạn chế | Đầy đủ hơn qua Car API |

Cả hai dùng chung **Android for Cars App Library** (`androidx.car.app`) và kiến trúc media.

### Tuần 22 – Tổng quan & môi trường
- [ ] Đọc developer.android.com/training/cars (toàn bộ phần overview)
- [ ] Cài **DHU** để test Android Auto; tạo **AAOS emulator** (system image Automotive)
- [ ] Hiểu các loại app được phép: media, nhắn tin, điều hướng, POI (điểm quan tâm), IoT, thời tiết; trên AAOS còn có video/game/trình duyệt khi xe đỗ
- [ ] Hiểu **driver distraction** (chống xao nhãng) và hướng dẫn chất lượng app ô tô — lý do UI ô tô bị giới hạn bằng template

### Tuần 23–24 – Project A1: Media app lên xe
- [ ] Đưa Project 6 (Nghe nhạc) chạy trên Android Auto (DHU) và AAOS emulator
- [ ] Khai báo manifest cho ô tô, cấu trúc cây duyệt nội dung, tìm kiếm bằng giọng nói
- **Xong khi:** duyệt và phát nhạc được từ màn hình xe trên cả DHU lẫn AAOS emulator

### Tuần 25–26 – Project A2: App POI bằng Car App Library
- [ ] `CarAppService`, `Session`, `Screen`, `ScreenManager`
- [ ] Template: `ListTemplate`, `GridTemplate`, `PaneTemplate`, `MessageTemplate`, template bản đồ
- [ ] Ý tưởng: **Tìm trạm sạc / bãi đỗ xe** — danh sách địa điểm gần đây → chi tiết → bấm "Dẫn đường" (gửi intent sang app navigation)
- **Xong khi:** chạy được cả trên DHU và AAOS emulator, tuân thủ giới hạn số bước/số item của template

### Tuần 27–29 – Project A3: Dashboard xe trên AAOS (project chính để đưa vào CV)
- [ ] **Car API** (`android.car`): `Car`, `CarPropertyManager`, `VehiclePropertyIds`
- [ ] Đọc dữ liệu: tốc độ, số (gear), mức pin/nhiên liệu, trạng thái đỗ xe…; đăng ký callback và chuyển thành `Flow`
- [ ] Quyền ô tô (`android.car.permission.CAR_SPEED`, `CAR_ENERGY`…)
- [ ] `CarUxRestrictionsManager`: ẩn/khoá chức năng khi xe đang chạy
- [ ] UI Compose: đồng hồ tốc độ vẽ bằng `Canvas`, thẻ thông tin pin, lịch sử chuyến đi lưu Room
- [ ] Giả lập dữ liệu xe bằng bảng điều khiển của emulator (Car data / VHAL properties)
- **Xong khi:** đổi tốc độ/số trong emulator thì UI cập nhật realtime; khi "xe đang chạy" các thao tác phức tạp bị khoá

> Lưu ý: một số thuộc tính (ví dụ điều khiển điều hoà HVAC) cần quyền hệ thống — app thường không dùng được, phải là system app (xem Giai đoạn 8).

---

## Giai đoạn 8 — Nâng cao (tuỳ chọn, hướng đi làm ở hãng xe / Tier-1)

- [ ] Kiến trúc AAOS: App → Car API → **CarService** → **VHAL** → ECU/CAN bus (đọc source.android.com/docs/automotive)
- [ ] Build **AOSP** bản automotive cho emulator (cần Linux hoặc WSL2, RAM ≥ 32 GB, ổ trống ≥ 300 GB)
- [ ] Viết **system/privileged app**, thêm một vehicle property tuỳ chỉnh vào VHAL
- [ ] Kiến thức ngành: CAN bus, diagnostic (UDS/OBD-II), khái niệm an toàn chức năng (ISO 26262), SOME/IP
- [ ] C++ cơ bản (tầng HAL/native viết bằng C++)

---

## Tài liệu chính (ưu tiên nguồn chính thức, miễn phí)

| Chủ đề | Nguồn |
|---|---|
| Kotlin | kotlinlang.org/docs · Kotlin Koans |
| Android + Compose | developer.android.com/courses (Android Basics with Compose) |
| Kiến trúc | developer.android.com/topic/architecture |
| App ô tô | developer.android.com/training/cars |
| AAOS hệ thống | source.android.com/docs/automotive |
| Code mẫu | GitHub: `android/car-samples`, `android/nowinandroid`, `android/compose-samples` |

## Cách theo dõi tiến độ

1. Tick `[x]` vào từng mục trong file này khi xong.
2. Mỗi project một thư mục riêng trong `D:\ANDROID_LEARN` (ví dụ `01-java-garage`, `04-compose-notes`, `A3-car-dashboard`) và một repo GitHub.
3. Cuối mỗi tuần viết 3 dòng: đã học gì – vướng gì – tuần sau làm gì.
