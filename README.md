# android-tuando

Name: Đỗ Thanh Tuấn Team: Mobile - Android(Kotlin) Onboarding: 03/10/2022 
 
## 1. Cài đặt git
#### MacOS
  - Đối với Mac, bạn có thể sử dụng file installer tải tại địa chỉ http://git-scm.com/download/mac để cài đặt.
#### Window
  - Nếu bạn dùng Windows thì có thể tải file .exe cài đặt Git tại địa chỉ http://git-scm.com/download/win. Khi cài bạn có thể để nguyên tùy chọn mặc định mà không cần tùy chỉnh gì thêm nếu bạn chưa hiểu về nó.
  - Sau khi cài đặt Git vào Windows, bạn sẽ cần mở ứng dụng Git Bash lên để bắt đầu sử dụng các dòng lệnh của Git.
#### Linux
``` sh  
    sudo apt-get install git
```

## 2. Thiết lập chứng thực cá nhân
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

## 3. Các trạng thái của file trong git

#### Staging Area
  - **Staging Area** nghĩa là một khu vực mà nó sẽ được chuẩn bị cho quá trình commit.
  - Muốn commit tập tin nào thì tập tin đó phải nằm trong **Staging Area**.
  - Một tập tin khi nằm trong **Staging Area** sẽ có trạng thái là **Stagged**.

  ![stage_are](https://thachpham.com/wp-content/uploads/2015/04/git-staging-area.png)
  
  
#### Bỏ qua Staging Are để commit
  - Một tập tin sau khi được thay đổi hay tạo mới thì nó phải được thêm vào **Staging Area** với lệnh git add. Tuy nhiên, bạn có thể đưa một tập tin đã được **Tracked** để commit mà không cần đưa nó vào **Staging Area** với tham số -a trong lệnh git commit.
``` kt
    $ git commit -a -m "Skipped Staging Are to commit"
```

#### Untracked
- Nếu bạn tạo ra hoặc thêm vào một tập tin mới vào trong thư mục làm việc của bạn thì nó sẽ ở trạng thái **Untracked**
- Để đưa nó về **Tracked** bạn sẽ sử dụng lệnh git add

#### Tracked
- Một khi một tập tin đã được đưa về Tracked thì nó sẽ có thể thay đổi giữa 3 trạng thái khác nhau là **Modified**, **Unmodified** và **Staged**.

![stage](https://thachpham.com/wp-content/uploads/2015/04/git-lifecycle.png)

## 4. Các lệnh thực thi cơ bản

#### Tạo repository
``` git
    $ git init repo_name
```

#### Add file vào staged
``` git
    $ git add readme.txt
```

#### Kiểm tra status
``` git
    $ git status
```

#### Commit file
``` git
    $ git commit -m "any message"
```

#### Push file
- **Origin** nghĩa là tên remote và **master** là tên branch
``` git
    $ git push origin master
```

#### Git log
- Để xem lịch sử của các lần commit trước đó
- Ngoài ra, bạn có thể chèn thêm tham số -p vào để hiển thị chi tiết của mỗi lần commit.
``` git
    $ git log
    commit 3f1ef84ada3dfd936735d8724f9bbb3437c77b19
    Author: Thach Pham <contact@thachpham.com>
    Date: Tue Apr 21 17:16:37 2015 -0700

    Hihi

    commit 6e729a49a36b31919daa6263f8f98f3a59d5bab3
    Author: Thach Pham <contact@thachpham.com>
    Date: Tue Apr 21 14:47:47 2015 -0700

    First commit on Github
```

#### Undo Commit
-   Nếu bạn cần xóa bỏ lần commit trước và cần undo để commit lại thì có thể sử dụng tham số --amend trong lệnh git commit







