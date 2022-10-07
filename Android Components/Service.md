## Tổng quan Service
#### 1. Khái niệm Service
Một Service là một thành phần (component) có thể thực hiện các hoạt động lâu dài trong background và nó không cung cấp một giao diện người dùng. Một kh đã chạy, nó sẽ tiếp tục chạy trong background ngay cả khi người dùng chuyển sang ứng dụng khác. Ngoài ra một thành phần có thể liên kết (bind) với một Service để tương tác với Service đó, thậm chí là thực hiện truyền thông liên tiến trình IPC (interprocess communication - IPC bạn có thể hiểu là một hoạt động chia sẽ dữ liệu qua nhiều tiến trình, thông thường sử dụng giao thức truyền thông và nó phải có Client và Server). Ví dụ: một Service có thể thực hiện các giao dịch mạng, chơi nhạc, ra vào file I/O hoặc tương tác với một content provider, tất cả đều từ background.

#### 2. Phân loại Service
Có 3 loại service khác nhau:
- ##### Foreground Service
  - **Một Foreground Service thực hiện một số thao tác mà người dùng chú ý, có thể thấy rõ ràng**. Ví dụ một ứng dụng nghe nhạc có thể chơi một bản nhạc và control nó bằng Foreground Service. Một điều bắt buộc là Foreground Service phải hiện thị một Notification. **Foreground Service sẽ tiếp tục chạy ngay cả khi người dùng không tương tác với ứng dụng.**

  - Khi sử dụng Foreground Service, bạn phải hiển thị một thông báo để người dùng nhận biết rằng service đang chạy, thông báo này không thể dismiss cho đến khi service dừng loại hoặc bị gỡ khỏi foreground
- ##### Background Service
    - **Một Background Service sẽ thực hiện các hoạt động mà không được người dùng chú ý trực tiếp**. Ví dụ một ứng dụng sử dụng một service để thu gom bộ nhớ chẳng hạn thì service là một Background Service, hoạt động mà người dùng không cần thiết phải để ý.
- ##### Bound Service
  - **Một service được gọi là Bound khi một application component ràng buộc với nó bởi lời gọi bindService()**. Một Bound Service cung cấp một giao diện client-server cho phép các **component** tương tác với nó: gửi yêu cầu, nhận kết quả và thậm chí là IPC. Một Bound Service chỉ chạy miễn là có một **application component** ràng buộc với nó. Có thể có nhiều **component** ràng buộc với Bound Service cùng lúc, nhưng khi tất cả tháo bỏ ràng buộc (unbound) thì nó sẽ Destroy
#### 2. Khác nhau giữa Service và Thread
  - Service có thể chạy trong background ngay cả khi người dùng đang không tương tác với ứng dụng
  - Thread được sử dụng để thực thi một công việc ngoài Main Thread nhưng chỉ trong khi người dùng đang tương tác với ứng dụng
#### 2. Các callback cơ bản
##### onStartCommand()
- Hệ thống gọi hàm này khi một **component** (ví dụ như Activity)s gọi hàm **startService()** để yêu cầu service bắt đầu. Sau khi gọi phương thức này, service sẽ bắt đầu và chạy trong background vô thời hạn. Để stop một service đang chạy, gọi stopSelf() bên trong service hoặc stopService() từ component
##### onBind()
- Hệ thống gọi hàm này khi một **component** (ví dụ như Activity) gọi hàm **bindService()** để kết nối tới một service. Khi bạn sử dụng phương thức này, phải cung một cho người dùng một giao diện để giao tiếp với Service bằng return một **IBinder**, để không cho phép binding thì trả về null
##### onCreate()
- Hệ thống gọi hàm này lần duy nhất khi service được khởi tạo, trước khi service gọi **onStartCommand()** hoặc **onBind()**. Nếu service đang chạy, phương thức này sẽ không được gọi.
##### onDestroy()
- Hệ thống gọi hàm này khi service đã không còn được sử dụng và bị hủy. Bạn nên thiết lập các trình dọn dẹp những thread, listener, receiver