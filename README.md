# android-tuando

Name: Đỗ Thanh Tuấn Team: Mobile - Android(Kotlin) Onboarding: 03/10/2022 
 
#### 1. Cài đặt git
- **MacOS**
  - Đối với Mac, bạn có thể sử dụng file installer tải tại địa chỉ http://git-scm.com/download/mac để cài đặt.
- **Window**
  - Nếu bạn dùng Windows thì có thể tải file .exe cài đặt Git tại địa chỉ http://git-scm.com/download/win. Khi cài bạn có thể để nguyên tùy chọn mặc định mà không cần tùy chỉnh gì thêm nếu bạn chưa hiểu về nó.
  - Sau khi cài đặt Git vào Windows, bạn sẽ cần mở ứng dụng Git Bash lên để bắt đầu sử dụng các dòng lệnh của Git.
- **Linux**
``` shell  
    sudo apt-get install git
```

#### 2. Thiết lập chứng thực cá nhân
``` git
    $ git config --global user.name "Thach Pham"
    $ git config --global user.email "contact@thachpham.com"
```

- **Kiểm tra thông tin chứng thực**
``` shell
    $ cat ~/.gitconfig
        [user]
        name = Thach Pham
        email = contact@thachpham.com
```

#### 3.Các lệnh thực thi cơ bản
``` git
    //Tạo repo
    $ git init repo_name

    //Đưa file về trạng thái Tracked
    $ git add readme.txt
    $ git status
    
```



