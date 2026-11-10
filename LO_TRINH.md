# Lộ trình học Android → Automotive

**Mục tiêu:** Java → Kotlin → Android nền tảng → Jetpack Compose → làm project app trên ô tô (Android Auto / Android Automotive OS).

**Giả định:** đã biết lập trình cơ bản (biến, vòng lặp, hàm), học ~10–15 giờ/tuần. Tổng ~30 tuần (≈ 7 tháng). Học nhiều giờ hơn thì rút ngắn tương ứng.

**Nguyên tắc học:** 30% đọc/xem – 70% gõ code. Mỗi giai đoạn kết thúc bằng một project nhỏ, đẩy lên GitHub.

**Cách đọc:** mỗi mục `[ ]` là một task; các gạch đầu dòng thụt vào bên dưới là chi tiết của task đó (học gì → thực hành gì → **Xong khi** nào thì được tick).

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
  - Tải ở developer.android.com/studio, chọn kiểu cài **Standard** — wizard tự tải Android SDK, Platform-Tools và Emulator
  - Mở SDK Manager (Settings → Languages & Frameworks → Android SDK), kiểm tra đã có SDK Platform bản mới nhất
  - Không cần cài JDK riêng cho Android: Gradle dùng JDK đi kèm Android Studio
  - **Xong khi:** mở được Android Studio, SDK Manager không báo thiếu gì
- [ ] Tạo emulator điện thoại (Pixel, API mới nhất), chạy project mẫu "Empty Activity"
  - Device Manager → Create Virtual Device → chọn một máy Pixel → system image API mới nhất (x86_64, Google APIs)
  - New Project → **Empty Activity** (mẫu Compose, ngôn ngữ Kotlin) → chờ Gradle sync xong → bấm Run ▶
  - Sửa dòng chữ "Hello Android" thành tên mình rồi chạy lại để thấy vòng sửa → chạy
  - Emulator chậm/không lên: kiểm tra đã bật ảo hoá trong BIOS (xem ghi chú bên dưới)
  - **Xong khi:** app hiện đúng dòng chữ đã sửa trên emulator
- [x] Cài **Git**, tạo tài khoản GitHub, học: `clone`, `add`, `commit`, `push`, `branch`
  - Cấu hình một lần: `git config --global user.name` và `user.email`
  - Vòng lặp hằng ngày: `git status` → `git add` → `git commit -m "..."` → `git push`
  - Nhánh: `git switch -c ten-nhanh`, làm xong thì merge về `main`; viết `.gitignore` (bỏ `build/`, `.gradle/`, `.idea/`)
  - **Xong khi:** thư mục học này đã nằm trên GitHub và push không lỗi
- [ ] Làm quen Android Studio: Logcat, Debugger (breakpoint), Gradle sync
  - **Logcat:** thêm `Log.d("HocAndroid", "...")` vào `onCreate`, lọc bằng `package:mine` và theo tag
  - **Debugger:** đặt breakpoint, chạy Debug 🐞, dùng Step Over / Step Into, xem giá trị biến ở khung Variables
  - **Gradle:** phân biệt `settings.gradle.kts`, `build.gradle.kts` của project và của module `app`; thử thêm 1 dependency rồi Sync
  - Phím tắt nên nhớ: Shift×2 (tìm mọi thứ), Alt+Enter (gợi ý sửa), Ctrl+B (nhảy tới định nghĩa), Shift+F10 (Run)
  - **Xong khi:** tự in được log, dừng được ở breakpoint và đọc được giá trị một biến

> Máy nên có RAM ≥ 16 GB, SSD trống ≥ 50 GB. Bật ảo hoá (VT-x/AMD-V) trong BIOS để emulator chạy mượt.

---

## Giai đoạn 1 — Java nền tảng (3 tuần)

Vì sao vẫn học Java: phần lớn mã nguồn hệ thống Android (AOSP), Car Service và rất nhiều code cũ ở các hãng xe viết bằng Java. Làm automotive **bắt buộc đọc được Java**.

**Tuần 1 – Cú pháp & OOP**
- [ ] Kiểu dữ liệu, toán tử, điều kiện, vòng lặp, mảng, String
  - Bài học: `01-java/tuan1/BAI_HOC.md` buổi 1–2; chạy và sửa thử `vidu/Ngay1_BienVaKieu.java`, `Ngay2_DieuKienVongLap.java`
  - Nắm chắc: kiểu nguyên thuỷ vs kiểu tham chiếu, ép kiểu (`int` ↔ `double`), chia số nguyên, `==` vs `equals()` với String
  - `if/else`, `switch`, `for`, `while`, for-each; mảng 1 chiều; `String` bất biến, `StringBuilder` khi nối nhiều
  - **Xong khi:** tự làm xong `BaiTap1_QuangDuong` và `BaiTap2_ThongKeTocDo` mà không xem lời giải
- [ ] Class, object, constructor, `static`, `final`
  - Bài học buổi 3; ví dụ `vidu/Ngay3_ClassVaObject.java`
  - Field, method, constructor (nhiều constructor, `this(...)`), từ khoá `this`
  - `static` = thuộc về class, không thuộc object; `final` cho biến / method / class; hằng số `static final`
  - Đóng gói: field `private` + getter/setter có kiểm tra giá trị hợp lệ
  - **Xong khi:** làm xong `BaiTap3_BinhNhienLieu`
- [ ] 4 tính chất OOP: đóng gói, kế thừa, đa hình, trừu tượng
  - Bài học buổi 4; ví dụ `vidu/Ngay4_KeThua.java`
  - `extends`, `super(...)`, `@Override`; `protected`; mọi class đều kế thừa `Object` (`toString`, `equals`, `hashCode`)
  - Đa hình: biến kiểu cha giữ object con, gọi method → chạy bản của con; `instanceof` và ép kiểu
  - **Xong khi:** làm xong `BaiTap4_BaiGuiXe` và giải thích được bằng lời 4 tính chất, mỗi cái kèm 1 ví dụ về xe
- [ ] `interface` vs `abstract class`
  - Bài học buổi 5; ví dụ `vidu/Ngay5_Interface.java`
  - Interface = "làm được gì" (một class implements nhiều interface); abstract class = "là gì" + dùng chung code/field
  - `default` method trong interface; mẫu callback/listener — Android dùng ở khắp nơi (`OnClickListener`…)
  - **Xong khi:** làm xong `BaiTap5_BangDieuKhien` và trả lời hết phần "Tự kiểm tra cuối tuần" trong `BAI_HOC.md`

**Tuần 2 – Thư viện chuẩn**
- [ ] Collections: `List`, `Set`, `Map` (ArrayList, HashMap…)
  - `ArrayList` vs `LinkedList`; `HashSet` (không trùng); `HashMap` (key → value); `TreeMap`/`LinkedHashMap` khi cần thứ tự
  - Duyệt bằng for-each và `Iterator`; sắp xếp với `Comparable` và `Comparator`
  - Vì sao object làm key của `HashMap` phải override cả `equals()` lẫn `hashCode()`
  - Thực hành: danh sách xe → nhóm theo hãng bằng `Map<String, List<Car>>`, sắp xếp theo năm sản xuất
  - **Xong khi:** chọn đúng List/Set/Map cho 3 bài toán khác nhau và giải thích được lý do
- [ ] Generics, Exception (`try/catch/finally`, checked vs unchecked)
  - Generics: `class Box<T>`, generic method, giới hạn `<T extends Vehicle>`; hiểu sơ `? extends` / `? super`
  - Exception: `try/catch/finally`, `throw` vs `throws`, try-with-resources
  - Checked (bắt buộc xử lý, vd `IOException`) vs unchecked (`RuntimeException`, vd `NullPointerException`)
  - Thực hành: tự viết `InvalidSpeedException`, ném ra khi tốc độ âm; đọc hiểu một stack trace từ trên xuống
  - **Xong khi:** nhìn stack trace chỉ ra được dòng gây lỗi trong code của mình
- [ ] Lambda, functional interface, Stream cơ bản
  - Lambda `(a, b) -> a + b`, method reference `Car::getName`
  - Functional interface có sẵn: `Runnable`, `Comparator`, `Predicate`, `Function`, `Consumer`, `Supplier`
  - Stream: `filter`, `map`, `sorted`, `collect(Collectors.toList())`, `count`, `anyMatch`
  - Thực hành: viết lại 3 vòng lặp `for` của bài trước bằng Stream
  - **Xong khi:** đọc được một chuỗi `.stream().filter().map().collect()` và nói được kết quả
- [ ] Đọc/ghi file
  - `Files.readAllLines`, `Files.writeString`, `Path`; `BufferedReader`/`BufferedWriter` cho file lớn
  - Luôn đóng file bằng try-with-resources; chỉ rõ bảng mã UTF-8
  - Thực hành: lưu danh sách xe ra file CSV rồi đọc lại thành `List<Car>` (dùng `split(",")`)
  - **Xong khi:** tắt chương trình, mở lại vẫn còn nguyên dữ liệu

**Tuần 3 – Đa luồng & project**
- [ ] `Thread`, `Runnable`, `synchronized`, `ExecutorService` (hiểu khái niệm, không cần sâu)
  - Tạo luồng bằng `Thread` + `Runnable`; `start()` khác `run()`; `sleep`, `join`
  - Race condition: 2 luồng cùng tăng 1 biến đếm → kết quả sai; sửa bằng `synchronized` hoặc `AtomicInteger`
  - `ExecutorService` (thread pool): `submit`, `Future`, `shutdown`
  - Liên hệ Android: UI chỉ chạy trên **main thread**, việc nặng phải đẩy sang luồng khác — sau này dùng coroutines
  - **Xong khi:** tự tái hiện được race condition rồi sửa được nó
- [ ] Inner class, anonymous class, callback/listener pattern (Android dùng rất nhiều)
  - Inner class (giữ tham chiếu tới object ngoài) vs static nested class (không giữ)
  - Anonymous class: `new OnSpeedChangedListener() { ... }` và bản rút gọn bằng lambda
  - Tự viết đủ bộ: interface listener + `setListener()` + nơi gọi `listener.onXxx()`
  - **Xong khi:** viết được `SpeedSensor` báo tốc độ cho nhiều listener đã đăng ký (có cả huỷ đăng ký)
- [ ] **Project 1:** App console *Quản lý gara xe* — thêm/sửa/xoá/tìm xe, lưu ra file, dùng interface + kế thừa (Car, Truck, ElectricCar…)
  - Thư mục `01-java-garage`; menu console đọc lệnh bằng `Scanner`
  - Mô hình: `abstract class Vehicle` → `Car`, `Truck`, `ElectricCar`; interface `Chargeable` cho xe điện
  - Chức năng: thêm / sửa / xoá / tìm theo biển số / lọc theo loại / sắp xếp; lưu và nạp từ file
  - Xử lý nhập sai (chữ thay vì số, biển số trùng) bằng exception, chương trình không được crash
  - **Xong khi:** chạy đủ các chức năng, dữ liệu còn sau khi mở lại, code đã đẩy lên GitHub kèm README ngắn

---

## Giai đoạn 2 — Kotlin (3 tuần)

**Tuần 4 – Cơ bản**
- [ ] `val/var`, null safety (`?`, `?.`, `?:`, `!!`), string template
  - `val` (không gán lại) vs `var`; suy luận kiểu; kiểu cơ bản `Int`, `Double`, `String`, `Boolean`
  - Kiểu nullable `String?`; gọi an toàn `?.`; giá trị mặc định `?:`; smart cast sau khi kiểm tra `!= null`
  - Hạn chế `!!` — mỗi lần dùng là một chỗ có thể crash
  - String template `"Tốc độ: $speed km/h"`, `"${car.name}"`; chuỗi nhiều dòng `"""..."""`
  - **Xong khi:** làm xong phần *Introduction* của Kotlin Koans
- [ ] Hàm, default/named argument, `when`, range
  - `fun`, hàm một biểu thức `fun f(x: Int) = x * 2`, kiểu trả về `Unit`
  - Tham số mặc định + gọi theo tên → thay cho nhiều overload của Java
  - `when` dùng như biểu thức (thay `switch`); `if` cũng là biểu thức
  - Range: `1..10`, `until`, `downTo`, `step`; `for (i in list.indices)`, `for ((i, v) in list.withIndex())`
  - **Xong khi:** viết lại được 3 method Java của Project 1 thành hàm Kotlin ngắn hơn
- [ ] Class, `data class`, `object`, `companion object`, `enum`, `sealed class`
  - Primary constructor, property (không cần getter/setter tay), khối `init`; class mặc định `final` → muốn kế thừa phải `open`
  - `data class`: tự có `equals`/`hashCode`/`toString`/`copy`, destructuring
  - `object` (singleton), `companion object` (thay cho `static`), `enum class`
  - `sealed class`/`sealed interface` + `when` đầy đủ nhánh — sau này dùng cho UI state (Loading / Success / Error)
  - **Xong khi:** mô hình hoá được `Vehicle` và một `sealed interface ChargeState` bằng Kotlin

**Tuần 5 – Kotlin "đúng chất"**
- [ ] Lambda, higher-order function, extension function
  - Kiểu hàm `(Int) -> String`; lambda `{ x -> x * 2 }`, tham số ngầm `it`; trailing lambda
  - Higher-order function: hàm nhận hoặc trả về hàm; biết `inline` tồn tại để làm gì
  - Extension function: `fun Double.toKmh() = this * 3.6` — thêm hàm cho class có sẵn mà không kế thừa
  - **Xong khi:** tự viết được một hàm `retry(times) { ... }` nhận lambda
- [ ] Collections API: `map`, `filter`, `groupBy`, `fold`…
  - `listOf` (chỉ đọc) vs `mutableListOf`; tương tự cho `Map`, `Set`
  - `map`, `filter`, `first`/`firstOrNull`, `any`/`all`, `sortedBy`, `groupBy`, `associateBy`, `sumOf`, `fold`, `flatMap`, `zip`
  - `Sequence` (lười) khi dữ liệu lớn hoặc chuỗi xử lý dài
  - **Xong khi:** làm xong phần *Collections* của Kotlin Koans
- [ ] Scope functions: `let`, `apply`, `run`, `also`, `with`
  - Hai câu hỏi để phân biệt: bên trong gọi object là `this` hay `it`? Trả về object hay kết quả lambda?
  - Dùng nhiều nhất: `?.let { }` để xử lý null, `apply { }` để cấu hình object, `also { }` để log/phụ
  - Không lồng nhiều tầng — code sẽ khó đọc hơn cả viết thường
  - **Xong khi:** tự lập được bảng 5 hàm (this/it, trả về gì, dùng khi nào) mà không tra tài liệu
- [ ] Generics, delegation (`by lazy`), gọi qua lại Java ↔ Kotlin
  - Generics: `class Box<T>`, `out`/`in` (mức đọc hiểu), `reified` trong inline function
  - Delegation: `by lazy { }`, `lateinit var`, class delegation `class A(b: B) : B by b`
  - Interop: gọi class Java từ Kotlin (chú ý kiểu `String!` có thể null); `@JvmStatic`, `@JvmOverloads` khi Java gọi Kotlin
  - **Xong khi:** một project chạy được với 1 class Java và 1 class Kotlin gọi lẫn nhau

**Tuần 6 – Coroutines & Flow**
- [ ] `suspend`, `launch`, `async/await`, `Dispatchers`, structured concurrency
  - Hàm `suspend` = tạm dừng được mà không chặn luồng; `delay` khác `Thread.sleep`
  - `runBlocking` (chỉ cho `main`/test), `launch` (không cần kết quả), `async`/`await` (cần kết quả, chạy song song)
  - `Dispatchers.Main` / `IO` / `Default`; đổi luồng bằng `withContext`
  - Structured concurrency: `CoroutineScope`, `Job`, huỷ scope cha → huỷ hết con; `try/catch` trong coroutine
  - **Xong khi:** gọi 2 tác vụ giả lập 1 giây song song bằng `async`, tổng thời gian ≈ 1 giây chứ không phải 2
- [ ] `Flow`, `StateFlow`, `SharedFlow` — **rất quan trọng** cho Compose và dữ liệu xe (tốc độ, pin… là luồng dữ liệu)
  - Cold flow: `flow { emit(...) }` — chỉ chạy khi có `collect`
  - Toán tử: `map`, `filter`, `onEach`, `combine`, `debounce`, `distinctUntilChanged`, `catch`
  - `StateFlow` (luôn có giá trị hiện tại → hợp với UI state) vs `SharedFlow` (sự kiện một lần)
  - `MutableStateFlow` + `update { }`; `stateIn` để biến cold flow thành `StateFlow`
  - **Xong khi:** giải thích được cold vs hot flow và khi nào dùng `StateFlow`, khi nào `SharedFlow`
- [ ] **Project 2:** Viết lại Project 1 bằng Kotlin; thêm "giả lập cảm biến" phát tốc độ xe bằng `Flow` mỗi 500 ms
  - Thư mục `02-kotlin-garage`, project Gradle (Kotlin JVM), thêm `kotlinx-coroutines-core`
  - Chuyển model sang `data class`/`sealed class`, xử lý danh sách bằng Collections API, bỏ hết `!!`
  - `fun speedSensor(): Flow<Int>` phát tốc độ ngẫu nhiên mỗi 500 ms; collect để in ra, cảnh báo khi > 120 km/h
  - Dùng `combine` ghép tốc độ với mức pin giả lập thành một trạng thái `VehicleState`
  - **Xong khi:** đủ chức năng như Project 1, code ngắn hơn rõ rệt, cảm biến chạy và dừng (cancel) được sạch sẽ

Tài liệu: kotlinlang.org/docs, Kotlin Koans (bài tập tương tác).

---

## Giai đoạn 3 — Android nền tảng (4 tuần)

**Tuần 7 – Activity & UI cơ bản**
- [ ] Cấu trúc project, `AndroidManifest.xml`, Gradle, resources
  - Cây thư mục: `app/src/main/java`, `res/`, `AndroidManifest.xml`, `build.gradle.kts`, `libs.versions.toml`
  - Manifest: khai báo `<application>`, `<activity>`, intent-filter LAUNCHER, permission
  - Gradle: `minSdk` / `targetSdk` / `compileSdk`, `applicationId`, dependencies, build type debug/release
  - Resources: `strings.xml`, `drawable`, `mipmap`, qualifier (`values-night`, `values-vi`, `layout-land`)
  - **Xong khi:** mở một project mới và giải thích được vai trò từng file chính
- [ ] Activity lifecycle (onCreate → onDestroy), xoay màn hình, lưu state
  - Đặt `Log.d` vào `onCreate/onStart/onResume/onPause/onStop/onDestroy` rồi quan sát Logcat
  - Thử: bấm Home, mở app khác, xoay màn hình, bấm Back — ghi lại thứ tự callback mỗi trường hợp
  - Configuration change phá và tạo lại Activity → giữ state bằng `onSaveInstanceState` / `rememberSaveable` / ViewModel
  - **Xong khi:** app đếm số giữ nguyên giá trị sau khi xoay màn hình
- [ ] View/XML ở mức **đọc hiểu**: ConstraintLayout, RecyclerView (code ô tô cũ còn nhiều View)
  - Layout XML: `LinearLayout`, `FrameLayout`, `ConstraintLayout`; `findViewById` và ViewBinding
  - `RecyclerView`: Adapter, ViewHolder, LayoutManager — hiểu vì sao phải tái sử dụng view
  - Chỉ cần đọc hiểu và sửa được, không cần thành thạo — UI mới sẽ viết bằng Compose
  - **Xong khi:** làm được 1 màn hình danh sách bằng RecyclerView theo codelab và đọc hiểu được layout XML của người khác

**Tuần 8 – Điều hướng & Intent**
- [ ] Intent explicit/implicit, truyền dữ liệu giữa màn hình
  - Explicit: `Intent(this, DetailActivity::class.java)` + `putExtra` / `getStringExtra`
  - Implicit: `ACTION_VIEW` (mở web, bản đồ `geo:`), `ACTION_SEND` (chia sẻ), `ACTION_DIAL`
  - Nhận kết quả bằng Activity Result API: `registerForActivityResult`
  - Intent filter và `<queries>` (package visibility từ Android 11)
  - **Xong khi:** app 2 màn hình truyền dữ liệu qua lại + 1 nút mở Google Maps bằng intent `geo:`
- [ ] Fragment (hiểu khái niệm), back stack
  - Fragment là gì, lifecycle riêng (`onCreateView` / `onViewCreated` / `onDestroyView`), vì sao code cũ dùng nhiều
  - `FragmentManager`, transaction `replace` + `addToBackStack`
  - Task và back stack của Activity; `launchMode` (mức biết là có)
  - **Xong khi:** đọc được code có Fragment và vẽ được back stack sau một chuỗi thao tác mở màn hình
- [ ] Runtime permission
  - Permission normal vs dangerous; khai báo `<uses-permission>` trong manifest
  - Xin quyền bằng `ActivityResultContracts.RequestPermission`; `shouldShowRequestPermissionRationale`
  - Xử lý đủ 3 trường hợp: cho phép / từ chối / từ chối vĩnh viễn (dẫn người dùng vào Settings)
  - **Xong khi:** app xin quyền vị trí hoặc thông báo và chạy đúng cả khi bị từ chối

**Tuần 9 – 4 thành phần Android** (nền tảng của mọi app ô tô)
- [ ] **Service**: started, bound, foreground service + notification
  - Started service: `startService`, `onStartCommand`, `START_STICKY`, `stopSelf`
  - Bound service: `bindService`, `onBind`, `ServiceConnection`, trả về `Binder` nội bộ
  - Foreground service: notification channel + `startForeground()`, quyền `FOREGROUND_SERVICE`, `foregroundServiceType`, quyền `POST_NOTIFICATIONS` (Android 13+)
  - Giới hạn chạy nền của Android hiện đại — khi nào dùng Service, khi nào dùng WorkManager
  - **Xong khi:** một foreground service đếm giây, hiện trên notification và vẫn chạy khi thoát app
- [ ] **BroadcastReceiver**, **ContentProvider** (mức khái niệm)
  - Receiver đăng ký động (`registerReceiver`) vs khai báo trong manifest; broadcast hệ thống (pin yếu, cắm sạc, khởi động xong)
  - ContentProvider: chia sẻ dữ liệu giữa các app qua URI `content://`; thử đọc danh bạ hoặc MediaStore bằng `ContentResolver`
  - Chỉ cần hiểu để đọc code, không cần tự viết provider
  - **Xong khi:** app phản ứng (in log / đổi UI) khi cắm–rút sạc trên emulator
- [ ] Context, Application class, process & vòng đời app
  - Application context vs Activity context — dùng sai gây memory leak (giữ Activity trong object sống lâu)
  - Class `Application`: `onCreate` chạy một lần cho mỗi process, nơi khởi tạo thứ dùng chung
  - Hệ thống có thể giết process khi thiếu RAM (process death) → state phải khôi phục được
  - Thử nghiệm: bật "Don't keep activities" trong Developer options rồi dùng app của mình
  - **Xong khi:** giải thích được vì sao không lưu Activity vào biến `static`/singleton

**Tuần 10 – Project**
- [ ] **Project 3:** App *Nhật ký chuyến đi* — danh sách chuyến đi, màn hình chi tiết, một foreground service đếm thời gian chuyến đi đang chạy
  - Thư mục `03-android-trip-log`; UI viết bằng View/XML hoặc Compose đơn giản đều được
  - Màn hình danh sách chuyến đi → bấm vào mở màn hình chi tiết (truyền id qua Intent)
  - Nút "Bắt đầu chuyến đi" chạy foreground service đếm thời gian; notification có nút "Kết thúc"
  - Dữ liệu tạm lưu trong bộ nhớ hoặc file JSON (Room để dành Giai đoạn 5)
  - **Xong khi:** thoát app service vẫn đếm, mở lại thấy đúng thời gian, kết thúc thì chuyến đi vào danh sách

---

## Giai đoạn 4 — Jetpack Compose (4 tuần)

**Tuần 11 – Tư duy Compose**
- [ ] `@Composable`, `@Preview`, `Modifier`
  - Tư duy khai báo: UI = f(state) — mô tả giao diện theo dữ liệu, không tự tay sửa view
  - Hàm `@Composable`, `setContent { }`, `@Preview` (nhiều preview: sáng/tối, cỡ chữ lớn)
  - `Modifier`: `padding`, `size`, `fillMaxWidth`, `background`, `clickable`, `weight` — **thứ tự có ý nghĩa**
  - **Xong khi:** dựng được một thẻ thông tin xe (ảnh + tên + biển số) chỉ bằng Preview, chưa cần chạy app
- [ ] Layout: `Column`, `Row`, `Box`, `Scaffold`
  - `Column`/`Row` với `Arrangement` và `Alignment`; `Spacer`; `Box` để xếp chồng
  - `Scaffold`: `topBar`, `bottomBar`, `floatingActionButton`, và phải dùng `innerPadding`
  - `Surface`, `Card`; edge-to-edge và `WindowInsets` (mức biết là có)
  - **Xong khi:** dựng lại được bố cục một màn hình của một app quen thuộc
- [ ] State: `remember`, `mutableStateOf`, recomposition, **state hoisting**
  - `var count by remember { mutableStateOf(0) }`; `rememberSaveable` để sống qua xoay màn hình
  - Recomposition: state đổi → các composable đọc state đó chạy lại; xem bằng Layout Inspector
  - State hoisting: composable con nhận `value` + `onValueChange`, state nằm ở cha → dễ test, dễ tái sử dụng
  - Stateless vs stateful composable
  - **Xong khi:** làm được counter + ô nhập liệu đã hoist state và giải thích được vì sao phải hoist

**Tuần 12 – Danh sách & Material 3**
- [ ] `LazyColumn`, `LazyRow`, `LazyVerticalGrid`
  - `items(list, key = { it.id })` — vì sao cần `key`; `itemsIndexed`, `stickyHeader`
  - `contentPadding`, `verticalArrangement = spacedBy(...)`, `rememberLazyListState` (cuộn tới vị trí)
  - Lưới: `GridCells.Fixed(n)` vs `GridCells.Adaptive(minSize)`
  - **Xong khi:** danh sách 1000 item cuộn mượt, bấm vào item đổi được trạng thái của nó
- [ ] Material 3: theme, color, typography, dark mode
  - `MaterialTheme`: `colorScheme`, `typography`, `shapes`; tạo bộ màu bằng Material Theme Builder
  - Dark mode: `isSystemInDarkTheme()`; dynamic color (Android 12+)
  - Luôn lấy màu/chữ từ theme (`MaterialTheme.colorScheme.primary`), không viết cứng mã màu
  - **Xong khi:** app đổi sáng/tối theo hệ thống mà không còn màu nào viết cứng
- [ ] TextField, Button, Card, Dialog, BottomSheet
  - `TextField`/`OutlinedTextField`: `keyboardOptions`, `isError`, `supportingText`
  - Các loại Button, `IconButton`, `FloatingActionButton`, `Checkbox`, `Switch`, `Slider`
  - `AlertDialog`, `ModalBottomSheet`, `Snackbar` qua `SnackbarHostState`
  - **Xong khi:** làm được form thêm xe có kiểm tra dữ liệu + dialog xác nhận xoá

**Tuần 13 – ViewModel & Navigation**
- [ ] `ViewModel` + `StateFlow` + `collectAsStateWithLifecycle`
  - `ViewModel` sống qua configuration change; `viewModelScope` để chạy coroutine
  - Mẫu chuẩn: `private val _uiState = MutableStateFlow(UiState())` + `val uiState = _uiState.asStateFlow()`
  - Trong UI: `val state by viewModel.uiState.collectAsStateWithLifecycle()`
  - `UiState` là `data class` bất biến, cập nhật bằng `update { it.copy(...) }`
  - **Xong khi:** chuyển state của màn hình tuần 12 vào ViewModel, xoay màn hình không mất dữ liệu
- [ ] Unidirectional Data Flow (UI state ↓, event ↑)
  - State đi xuống (ViewModel → UI), sự kiện đi lên (UI → ViewModel) qua lambda `onXxx`
  - Tách `Screen` (lấy ViewModel) và `Content` (stateless, chỉ nhận state + lambda) → Preview được
  - Gom sự kiện bằng `sealed interface UiEvent` khi màn hình có nhiều hành động
  - **Xong khi:** composable `Content` của bạn không tham chiếu tới ViewModel và có Preview chạy được
- [ ] Navigation Compose, truyền tham số
  - `NavHost`, `NavController`, `composable<Route>` với route type-safe (`@Serializable`)
  - `navigate`, `popBackStack`, `popUpTo`, `launchSingleTop`
  - Truyền **id** chứ không truyền cả object; màn hình đích tự tải dữ liệu theo id
  - Bottom navigation với nhiều tab
  - **Xong khi:** app danh sách → chi tiết → quay lại hoạt động đúng, kể cả nút Back hệ thống
- [ ] Side effects: `LaunchedEffect`, `rememberCoroutineScope`, `DisposableEffect`
  - `LaunchedEffect(key)`: chạy coroutine khi vào composition hoặc khi key đổi
  - `rememberCoroutineScope`: chạy coroutine từ sự kiện bấm (vd hiện Snackbar)
  - `DisposableEffect`: đăng ký/huỷ listener (`onDispose`) — sẽ dùng cho callback của xe
  - `derivedStateOf`, `snapshotFlow` (mức biết là có)
  - **Xong khi:** hiện Snackbar sau khi lưu, và đăng ký/huỷ một listener đúng vòng đời bằng `DisposableEffect`

**Tuần 14 – Hoàn thiện**
- [ ] Animation cơ bản, `Canvas` (vẽ đồng hồ tốc độ — sẽ dùng lại ở project ô tô)
  - `animateFloatAsState`, `AnimatedVisibility`, `animateContentSize`, `Crossfade`
  - `Canvas`: `drawArc`, `drawLine`, `drawCircle`, `rotate`; hệ toạ độ và góc tính theo độ
  - Vẽ đồng hồ tốc độ: cung tròn nền + vạch chia + kim quay theo giá trị, kim chuyển động mượt bằng animation
  - **Xong khi:** composable `Speedometer(speed: Float)` hiển thị đúng 0–240 km/h và kim chạy mượt khi đổi giá trị
- [ ] Responsive layout cho màn hình ngang/rộng (màn hình xe thường ngang, lớn)
  - Window size class (compact / medium / expanded); `BoxWithConstraints`
  - Bố cục list–detail 2 cột trên màn hình rộng, 1 cột trên điện thoại dọc
  - Test bằng Preview nhiều kích thước và emulator tablet / xoay ngang
  - **Xong khi:** cùng một màn hình hiển thị hợp lý ở cả điện thoại dọc lẫn màn hình ngang rộng
- [ ] **Project 4:** App *Ghi chú* bằng Compose — CRUD, tìm kiếm, dark mode, navigation
  - Thư mục `04-compose-notes`
  - Màn hình: danh sách ghi chú (có ô tìm kiếm) → thêm/sửa → xoá có xác nhận
  - ViewModel + StateFlow + Navigation; dữ liệu giữ trong bộ nhớ (Room sẽ thêm ở Giai đoạn 5)
  - Material 3, sáng/tối, bố cục 2 cột khi màn hình rộng
  - **Xong khi:** đủ CRUD + tìm kiếm, xoay màn hình không mất dữ liệu đang nhập, đã đẩy lên GitHub

Tài liệu chính: khoá **Android Basics with Compose** (developer.android.com/courses) — miễn phí, có codelab từng bước.

---

## Giai đoạn 5 — Kiến trúc & dữ liệu (4 tuần)

- [ ] **Tuần 15:** Kiến trúc MVVM + Repository, phân lớp UI / Domain / Data
  - Đọc "Guide to app architecture" (developer.android.com/topic/architecture)
  - Lớp UI (Compose + ViewModel) → lớp Data (Repository + data source); lớp Domain (use case) là tuỳ chọn
  - Repository là **nguồn sự thật duy nhất**, lộ dữ liệu ra bằng `Flow`; ViewModel không biết dữ liệu đến từ mạng hay database
  - Phụ thuộc vào interface (`NotesRepository`) để thay được bản giả khi test
  - **Xong khi:** tách Project 4 thành các package `ui` / `data`, ViewModel chỉ còn gọi Repository
- [ ] **Tuần 16:** **Room** (database), **DataStore** (cài đặt)
  - Room: `@Entity`, `@Dao` (`@Query`, `@Insert`, `@Update`, `@Delete`), `@Database`; cài bằng KSP
  - DAO trả về `Flow<List<T>>` → UI tự cập nhật khi dữ liệu đổi; hàm ghi là `suspend`
  - Migration khi đổi schema (mức cơ bản); xem dữ liệu bằng Database Inspector
  - DataStore Preferences: lưu cài đặt (dark mode, đơn vị km/h–mph) thay cho SharedPreferences
  - **Xong khi:** App Ghi chú lưu bằng Room — tắt hẳn app mở lại vẫn còn; có 1 cài đặt lưu bằng DataStore
- [ ] **Tuần 17:** **Retrofit** + kotlinx.serialization, xử lý lỗi/loading; **Hilt** (dependency injection)
  - Retrofit: interface API với hàm `suspend`, `@GET`, `@Query`, `@Path`; converter kotlinx.serialization; quyền `INTERNET`
  - Model `@Serializable`; tách DTO (dữ liệu mạng) khỏi model dùng trong app
  - `sealed interface UiState { Loading; Success; Error }`; bắt `IOException` / `HttpException`; có nút "Thử lại"
  - Hilt: `@HiltAndroidApp`, `@AndroidEntryPoint`, `@HiltViewModel`, `@Inject constructor`, `@Module` + `@Provides` / `@Binds`
  - **Xong khi:** gọi được một API công khai (vd Open-Meteo, không cần key), hiển thị đủ 3 trạng thái, mọi phụ thuộc được Hilt cấp
- [ ] **Tuần 18:** Unit test (JUnit, coroutine test), Compose UI test cơ bản; WorkManager
  - Unit test ViewModel với repository giả (fake): `runTest`, `Dispatchers.setMain`, kiểm tra `StateFlow` (thư viện Turbine)
  - Compose UI test: `createComposeRule`, `onNodeWithText(...).performClick()`, `assertIsDisplayed()`
  - WorkManager: `CoroutineWorker`, `OneTimeWorkRequest` / `PeriodicWorkRequest` (tối thiểu 15 phút), `Constraints` (có mạng, đang sạc)
  - **Xong khi:** có ≥ 5 unit test xanh cho một ViewModel + 1 UI test + 1 worker đồng bộ định kỳ
- [ ] **Project 5:** App *Thời tiết* — gọi API, cache offline bằng Room, MVVM + Hilt, có test cho ViewModel
  - Thư mục `05-weather`; API gợi ý: Open-Meteo (miễn phí, không cần key)
  - Màn hình: tìm thành phố → thời tiết hiện tại + dự báo vài ngày; lưu thành phố yêu thích
  - Offline-first: UI luôn đọc từ Room; Repository gọi mạng rồi ghi vào Room; mất mạng vẫn xem được dữ liệu cũ kèm giờ cập nhật
  - WorkManager làm mới dữ liệu định kỳ; DataStore lưu đơn vị °C/°F
  - **Xong khi:** bật chế độ máy bay vẫn xem được dữ liệu đã tải, test ViewModel xanh, đã đẩy lên GitHub

---

## Giai đoạn 6 — Cầu nối sang ô tô (3 tuần)

Những mảng Android mà app ô tô dùng nhiều nhất:

- [ ] **Tuần 19 – IPC:** Bound Service, **AIDL**, Binder (app ô tô nói chuyện với Car Service qua Binder)
  - Vì sao cần IPC: mỗi app một process riêng, không đọc được bộ nhớ của nhau; Binder là cơ chế gọi hàm xuyên process của Android
  - Viết file `.aidl` (bật `buildFeatures { aidl = true }`), implement `Stub` phía service, `Stub.asInterface(binder)` phía client
  - Làm **2 app riêng**: app server có service giả lập dữ liệu xe, app client `bindService` và gọi hàm; thêm callback `oneway` để server đẩy tốc độ về client
  - Lưu ý: lời gọi tới chạy trên binder thread (không phải main thread); `linkToDeath` khi process bên kia chết; `<queries>` để nhìn thấy app kia
  - **Xong khi:** app client hiển thị tốc độ do app server phát ra theo thời gian thực
- [ ] **Tuần 20 – Media:** **Media3** (ExoPlayer), `MediaSession`, `MediaLibraryService`, audio focus
  - Thư viện: `media3-exoplayer`, `media3-session`, `media3-ui`; `ExoPlayer` + `MediaItem` phát một file/URL mp3
  - `MediaSessionService` + `MediaSession`: đưa player vào foreground service (`foregroundServiceType="mediaPlayback"`) để phát nền, notification điều khiển tự sinh
  - UI nối với service qua `MediaController` (tạo từ `SessionToken`) — UI không giữ player trực tiếp
  - `MediaLibraryService`: `onGetLibraryRoot`, `onGetChildren` → cây duyệt nội dung (**chính là thứ màn hình xe sẽ hiển thị**)
  - Audio focus: `setAudioAttributes(attrs, /* handleAudioFocus = */ true)`; tự dừng khi rút tai nghe (`setHandleAudioBecomingNoisy`)
  - **Xong khi:** nhạc vẫn phát khi tắt màn hình, điều khiển được từ notification, tự tạm dừng khi có cuộc gọi/app khác phát tiếng
- [ ] **Tuần 21 – Vị trí & project:** Location API, bản đồ cơ bản
  - Quyền `ACCESS_COARSE_LOCATION` / `ACCESS_FINE_LOCATION` (xin lúc chạy); quyền nền là chuyện riêng, chưa cần
  - `FusedLocationProviderClient`: `lastLocation`, `requestLocationUpdates` → bọc thành `Flow` bằng `callbackFlow`
  - Giả lập vị trí và lộ trình trong emulator (Extended controls → Location)
  - Bản đồ: Maps Compose (cần API key) hoặc MapLibre/osmdroid (không cần key) — hiển thị marker vị trí hiện tại
  - **Xong khi:** app hiện vị trí hiện tại trên bản đồ và cập nhật khi đổi vị trí trong emulator
- [ ] **Project 6:** App *Nghe nhạc* trên điện thoại — phát nền, điều khiển từ notification/lock screen, duyệt thư viện qua `MediaLibraryService` (project này sẽ "lên xe" ở giai đoạn 7)
  - Thư mục `06-media-player`; nguồn nhạc: vài file mp3 trong `assets`/`raw` hoặc danh sách URL
  - `MediaLibraryService` với cây: Gốc → Album / Playlist → Bài hát; mỗi `MediaItem` đủ metadata (tên, nghệ sĩ, ảnh bìa)
  - UI Compose: danh sách bài, màn hình đang phát (play/pause, next/prev, thanh tua), mini player
  - Giữ kiến trúc MVVM + Hilt; UI chỉ nói chuyện với `MediaController`
  - **Xong khi:** phát nền ổn định, điều khiển được từ lock screen/notification, duyệt được thư viện bằng một `MediaBrowser` client

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
  - Đọc phần tổng quan + trang riêng của từng loại app (media, messaging, navigation, POI…)
  - Ghi chú lại: Android Auto khác AAOS thế nào về nơi chạy, cách phân phối, cách test
  - Xem qua repo mẫu `android/car-samples` để biết một app ô tô trông ra sao
  - **Xong khi:** tự viết được nửa trang tóm tắt "Android Auto vs AAOS" bằng lời của mình
- [ ] Cài **DHU** để test Android Auto; tạo **AAOS emulator** (system image Automotive)
  - DHU: SDK Manager → SDK Tools → *Android Auto Desktop Head Unit Emulator*; file chạy nằm ở `<SDK>/extras/google/auto/`
  - Trên điện thoại/emulator có Android Auto: bật developer mode (bấm nhiều lần vào Version) → *Start head unit server* → `adb forward tcp:5277 tcp:5277` → chạy `desktop-head-unit`
  - AAOS: Device Manager → Create Virtual Device → nhóm **Automotive** → chọn system image Automotive (bản có Google APIs/Play Store)
  - **Xong khi:** DHU hiện giao diện Android Auto, và AAOS emulator khởi động vào màn hình home của xe
- [ ] Hiểu các loại app được phép: media, nhắn tin, điều hướng, POI (điểm quan tâm), IoT, thời tiết; trên AAOS còn có video/game/trình duyệt khi xe đỗ
  - Mỗi loại app dùng cách xây dựng nào: media → `MediaLibraryService`; điều hướng/POI/IoT/thời tiết → Car App Library (template); video/game → app thường, chỉ dùng khi đỗ
  - Mỗi loại có điều kiện duyệt riêng trên Google Play (có loại còn ở giai đoạn beta/giới hạn)
  - Lập bảng: loại app | Android Auto có không | AAOS có không | xây bằng gì
  - **Xong khi:** với một ý tưởng app bất kỳ, xác định được nó thuộc loại nào và có được phép lên xe không
- [ ] Hiểu **driver distraction** (chống xao nhãng) và hướng dẫn chất lượng app ô tô — lý do UI ô tô bị giới hạn bằng template
  - Vì sao không được tự vẽ UI tuỳ ý khi xe chạy: an toàn + quy định → host (Android Auto/AAOS) vẽ UI từ template
  - Các giới hạn điển hình: số item trong danh sách, độ dài chữ, số bước của một tác vụ (tối đa 5 template), hạn chế nhập bàn phím khi xe chạy
  - Đọc *car app quality guidelines* (developer.android.com/docs/quality-guidelines/car-app-quality)
  - Khái niệm UX restrictions và trạng thái xe: đỗ (parked) / dừng (idling) / đang chạy (moving)
  - **Xong khi:** nêu được 5 quy tắc mà Project A2 và A3 phải tuân thủ

### Tuần 23–24 – Project A1: Media app lên xe
- [ ] Đưa Project 6 (Nghe nhạc) chạy trên Android Auto (DHU) và AAOS emulator
  - Android Auto: cài app lên điện thoại → nối DHU → app xuất hiện trong launcher của xe (bật *Unknown sources* trong developer settings của Android Auto)
  - AAOS: tạo thêm module `automotive` (New Module → Automotive) dùng chung code service với module điện thoại
  - Không phải viết UI: host tự vẽ giao diện duyệt và phát từ `MediaLibraryService` + `MediaSession`
  - Sửa lỗi hay gặp: thiếu ảnh bìa/metadata, cây duyệt quá sâu, không khôi phục được bài đang phát
  - **Xong khi:** thấy app trong DHU và AAOS emulator, bấm phát được một bài
- [ ] Khai báo manifest cho ô tô, cấu trúc cây duyệt nội dung, tìm kiếm bằng giọng nói
  - Android Auto: `<meta-data android:name="com.google.android.gms.car.application" android:resource="@xml/automotive_app_desc"/>` với `<uses name="media"/>`
  - AAOS: `<uses-feature android:name="android.hardware.type.automotive" android:required="true"/>` trong module automotive
  - Cây duyệt: đánh dấu `isBrowsable` / `isPlayable` cho từng `MediaItem`; gợi ý kiểu hiển thị danh sách/lưới (content style)
  - Tìm kiếm & giọng nói: implement `onSearch` / `onGetSearchResult` và xử lý `onAddMediaItems` (có `searchQuery`) cho câu "phát bài …"
  - Custom action (vd yêu thích, tua 30 giây) và khôi phục phiên phát (`onPlaybackResumption`)
  - **Xong khi:** tìm bài theo tên và phát được từ màn hình xe
- **Xong khi:** duyệt và phát nhạc được từ màn hình xe trên cả DHU lẫn AAOS emulator

### Tuần 25–26 – Project A2: App POI bằng Car App Library
- [ ] `CarAppService`, `Session`, `Screen`, `ScreenManager`
  - Thư viện: `androidx.car.app:app` + `app-projected` (Android Auto) / `app-automotive` (AAOS)
  - `CarAppService`: `createHostValidator()` + `onCreateSession()`; khai báo trong manifest với action `androidx.car.app.CarAppService` và category `androidx.car.app.category.POI`
  - `Session.onCreateScreen()` trả về màn hình đầu; `Screen.onGetTemplate()` trả về template; gọi `invalidate()` khi dữ liệu đổi
  - `ScreenManager.push()` / `pop()`; `Screen` có `lifecycle` như Activity; khai báo `minCarApiLevel`
  - **Xong khi:** app "Hello car" hiện một `MessageTemplate` trên DHU
- [ ] Template: `ListTemplate`, `GridTemplate`, `PaneTemplate`, `MessageTemplate`, template bản đồ
  - `ListTemplate` (`ItemList` + `Row`), `GridTemplate`, `PaneTemplate` (chi tiết + tối đa vài `Action`), `MessageTemplate` (lỗi/thông báo)
  - Template có bản đồ cho POI: `PlaceListMapTemplate` / `MapWithContentTemplate` (tuỳ phiên bản thư viện)
  - `ActionStrip`, `CarIcon`, `CarColor`; trạng thái loading bằng `setLoading(true)`
  - Hỏi giới hạn của host bằng `ConstraintManager.getContentLimit(...)` thay vì đoán số item
  - **Xong khi:** mỗi template trên đã được dùng thử ít nhất một lần trong app mẫu
- [ ] Ý tưởng: **Tìm trạm sạc / bãi đỗ xe** — danh sách địa điểm gần đây → chi tiết → bấm "Dẫn đường" (gửi intent sang app navigation)
  - Thư mục `A2-car-poi`; dữ liệu: file JSON các trạm sạc (tên, toạ độ, số cổng, giá) — chưa cần API thật
  - Màn 1: danh sách sắp theo khoảng cách (dùng vị trí từ Giai đoạn 6) → Màn 2: `PaneTemplate` chi tiết
  - Nút "Dẫn đường": `carContext.startCarApp(Intent(CarContext.ACTION_NAVIGATE, Uri.parse("geo:lat,lng")))`
  - Xin quyền vị trí trên xe bằng `carContext.requestPermissions(...)`; khi xe đang chạy thì hiện màn hình hướng dẫn thay vì bắt người lái thao tác
  - Luồng không quá 5 bước; có nút làm mới và trạng thái "không có kết quả"
  - **Xong khi:** từ danh sách → chi tiết → bấm dẫn đường mở được app bản đồ trên emulator
- **Xong khi:** chạy được cả trên DHU và AAOS emulator, tuân thủ giới hạn số bước/số item của template

### Tuần 27–29 – Project A3: Dashboard xe trên AAOS (project chính để đưa vào CV)
- [ ] **Car API** (`android.car`): `Car`, `CarPropertyManager`, `VehiclePropertyIds`
  - Thư mục `A3-car-dashboard`; app AAOS thuần (không chạy trên Android Auto); thêm `useLibrary("android.car")` trong `build.gradle.kts`
  - Kết nối: `Car.createCar(context)` → `car.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager`
  - Mỗi dữ liệu xe là một *property*: có id (`VehiclePropertyIds.*`), kiểu dữ liệu, area (toàn xe / từng ghế / từng bánh), quyền đọc/ghi
  - Liệt kê property mà emulator hỗ trợ bằng `getPropertyList()` và in ra Logcat
  - **Xong khi:** app in ra log danh sách property và đọc được một giá trị tĩnh (vd `INFO_MAKE`, `INFO_MODEL`)
- [ ] Đọc dữ liệu: tốc độ, số (gear), mức pin/nhiên liệu, trạng thái đỗ xe…; đăng ký callback và chuyển thành `Flow`
  - Property cần dùng: `PERF_VEHICLE_SPEED` (đơn vị **m/s** → ×3,6 ra km/h), `GEAR_SELECTION`, `EV_BATTERY_LEVEL` (Wh) + `INFO_EV_BATTERY_CAPACITY`, `FUEL_LEVEL`, `PARKING_BRAKE_ON`
  - Đọc một lần: `getProperty(...)`; theo dõi liên tục: `registerCallback(callback, propId, rate)` (API mới hơn: `subscribePropertyEvents`)
  - Bọc callback thành `Flow` bằng `callbackFlow { ...; awaitClose { unregisterCallback(cb) } }`
  - `VehicleRepository` lộ ra `StateFlow<VehicleState>`; xử lý property không có/không khả dụng (status khác `AVAILABLE`)
  - **Xong khi:** ViewModel nhận được luồng tốc độ + số + pin và in ra log khi đổi giá trị trong emulator
- [ ] Quyền ô tô (`android.car.permission.CAR_SPEED`, `CAR_ENERGY`…)
  - Khai báo trong manifest; tra bảng property ↔ permission trong tài liệu `VehiclePropertyIds`
  - `CAR_SPEED`, `CAR_ENERGY` là quyền *dangerous* → phải xin lúc chạy; `CAR_INFO`, `CAR_POWERTRAIN` (số) là quyền normal
  - Thiếu quyền thì đọc property ném `SecurityException` → UI phải có trạng thái "chưa cấp quyền" kèm nút cấp lại
  - Biết loại thứ ba: quyền signature/privileged (vd điều khiển điều hoà) — app thường không xin được
  - **Xong khi:** app xin quyền đúng lúc và không crash khi người dùng từ chối
- [ ] `CarUxRestrictionsManager`: ẩn/khoá chức năng khi xe đang chạy
  - Lấy bằng `car.getCarManager(Car.CAR_UX_RESTRICTION_SERVICE)`; đọc `currentCarUxRestrictions`, đăng ký `registerListener`
  - Dùng `isRequiresDistractionOptimization` và các cờ (`UX_RESTRICTIONS_NO_KEYBOARD`, `NO_VIDEO`, `LIMIT_STRING_LENGTH`…) để quyết định khoá gì
  - Activity muốn hiển thị khi xe chạy phải khai báo `<meta-data android:name="distractionOptimized" android:value="true"/>`
  - Bọc thành `Flow<Boolean>` "đang bị hạn chế" để UI Compose tự phản ứng
  - **Xong khi:** chuyển số sang D và tăng tốc trong emulator → màn hình lịch sử/cài đặt bị khoá kèm thông báo; về P thì mở lại
- [ ] UI Compose: đồng hồ tốc độ vẽ bằng `Canvas`, thẻ thông tin pin, lịch sử chuyến đi lưu Room
  - Dùng lại `Speedometer` của tuần 14; bố cục ngang cho màn hình xe; chữ lớn, tương phản cao, vùng bấm to (≥ 76 dp)
  - Thẻ pin: phần trăm = `EV_BATTERY_LEVEL` / `INFO_EV_BATTERY_CAPACITY`; thẻ số đang gài (P/R/N/D)
  - Chuyến đi: bắt đầu khi rời P, kết thúc khi về P; lưu thời gian, quãng đường (cộng dồn tốc độ × thời gian), tốc độ trung bình/tối đa vào Room
  - Kiến trúc: `VehicleRepository` (Car API) + `TripRepository` (Room) → ViewModel → Compose; Hilt cấp phụ thuộc
  - **Xong khi:** dashboard hiển thị đủ tốc độ/số/pin và có danh sách chuyến đi đã lưu
- [ ] Giả lập dữ liệu xe bằng bảng điều khiển của emulator (Car data / VHAL properties)
  - Emulator → Extended controls (⋮) → **Car data**: tab cảm biến (tốc độ, số, phanh tay) và tab *VHAL properties* để sửa từng property
  - Dòng lệnh: `adb shell cmd car_service inject-vhal-event <propId> <value>`; xem trạng thái bằng `adb shell dumpsys car_service`
  - Viết một kịch bản lái thử (file `.bat`/`.sh`): P → D → tăng tốc dần → giảm tốc → P để test lặp lại được
  - **Xong khi:** chạy kịch bản và quay được video demo dashboard phản ứng realtime — đưa vào README của project
- **Xong khi:** đổi tốc độ/số trong emulator thì UI cập nhật realtime; khi "xe đang chạy" các thao tác phức tạp bị khoá

> Lưu ý: một số thuộc tính (ví dụ điều khiển điều hoà HVAC) cần quyền hệ thống — app thường không dùng được, phải là system app (xem Giai đoạn 8).

---

## Giai đoạn 8 — Nâng cao (tuỳ chọn, hướng đi làm ở hãng xe / Tier-1)

- [ ] Kiến trúc AAOS: App → Car API → **CarService** → **VHAL** → ECU/CAN bus (đọc source.android.com/docs/automotive)
  - Đi theo đường đi của một giá trị tốc độ: ECU → CAN bus → VHAL → CarService → `CarPropertyManager` → app
  - CarService là system service viết bằng Java (`packages/services/Car`); VHAL là HAL định nghĩa bằng AIDL (`IVehicle`), thường viết bằng C++
  - Đọc code `CarPropertyService` trên cs.android.com — đây là lúc dùng tới kỹ năng đọc Java và Binder/AIDL
  - **Xong khi:** vẽ được sơ đồ các tầng và nói được mỗi tầng do ai viết (Google / hãng xe / nhà cung cấp)
- [ ] Build **AOSP** bản automotive cho emulator (cần Linux hoặc WSL2, RAM ≥ 32 GB, ổ trống ≥ 300 GB)
  - Cài `repo`, `repo init` nhánh Android mới → `repo sync` (rất lâu, nên để qua đêm)
  - `source build/envsetup.sh` → `lunch` target dạng `sdk_car_x86_64-…-userdebug` → `m` → chạy `emulator`
  - Tập sửa một thứ nhỏ (vd một chuỗi trong Car Launcher/Settings) → build lại riêng module → thấy thay đổi
  - **Xong khi:** emulator chạy bản AAOS tự build và có một thay đổi của chính mình
- [ ] Viết **system/privileged app**, thêm một vehicle property tuỳ chỉnh vào VHAL
  - App hệ thống: đặt trong cây AOSP với `Android.bp`, cài vào `/system/priv-app`, khai báo quyền trong file `privapp-permissions` XML
  - Dùng được quyền privileged/signature, vd `CAR_CONTROL_CLIMATE` để điều khiển HVAC
  - Thêm property vendor (nhóm `VENDOR` của `VehiclePropertyGroup`) vào cấu hình VHAL mẫu rồi đọc nó từ app
  - **Xong khi:** app của bạn đổi được nhiệt độ điều hoà trên emulator và đọc được property tự thêm
- [ ] Kiến thức ngành: CAN bus, diagnostic (UDS/OBD-II), khái niệm an toàn chức năng (ISO 26262), SOME/IP
  - CAN: frame, ID, cơ chế ưu tiên (arbitration), file DBC mô tả tín hiệu; CAN FD
  - Chẩn đoán: OBD-II (các PID chuẩn như tốc độ, vòng tua), UDS (ISO 14229: đọc mã lỗi DTC, session)
  - ISO 26262: mức ASIL A–D; vì sao infotainment (QM) được tách khỏi các hệ thống an toàn
  - SOME/IP: giao tiếp hướng dịch vụ trên Automotive Ethernet
  - **Xong khi:** đọc một bản mô tả công việc automotive mà không còn thuật ngữ nào lạ
- [ ] C++ cơ bản (tầng HAL/native viết bằng C++)
  - Con trỏ, tham chiếu, `const`, class, constructor/destructor, RAII
  - `std::unique_ptr` / `std::shared_ptr`, `std::vector`, `std::string`, `std::mutex`
  - Đọc code VHAL mẫu trong `hardware/interfaces/automotive/vehicle`
  - JNI ở mức biết: Java gọi xuống native thế nào
  - **Xong khi:** đọc hiểu được một hàm xử lý property trong VHAL mẫu

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

1. Tick `[x]` vào từng mục trong file này khi xong (hoặc tick trong app `tracker`).
2. Mỗi project một thư mục riêng trong `D:\ANDROID_LEARN` (ví dụ `01-java-garage`, `04-compose-notes`, `A3-car-dashboard`) và một repo GitHub.
3. Cuối mỗi tuần viết 3 dòng: đã học gì – vướng gì – tuần sau làm gì.
