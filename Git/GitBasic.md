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

``` git
    $ git log --pretsty="%s"
    Hihi
    First commit on Github
    $ git commit --amend -m "Hehe"
    [master 3682e56] Hehe
    Date: Tue Apr 21 17:16:37 2015 -0700
    2 files changed, 1 insertion(+), 1 deletion(-)
    create mode 100644 faq.html
    $ git log --pretty="%s"
    Hehe
    First commit on Github
```
- Lưu ý rằng undo nghĩa là bạn quay trở lại bước commit lần trước, do vậy nếu cần bổ sung tập tin nào vào để commit thì hãy đưa tập tin đó vào Staging Area trước.

#### Git tag

- Dùng để thẻ đánh dấu (tag) cho mỗi commit và khi cần xem bạn chỉ cần sử dụng lệnh ***git show tên_tag*** là đã có thông tin rất rõ ràng, ngoài ra nó còn giúp bạn dễ dàng diff (đối chiếu) sau này khi không cần nhớ checksum (dù chỉ cần nhớ vài ký tự đầu tiên) của mỗi commit mà chỉ cần nhớ tag

- Lightweight Tag và Annotated Tag

  - **Lightweight Tag**: Các tag này chỉ đơn thuần là đánh dấu snapshot của commit.
  - **Annotated Tag**: Với tag này, bạn có thể đặt tiêu đề cho tag, và khi xem nó sẽ có thông tin về người tag, ngày tag,….

##### Cách tạo Lightweight Tag
``` git
    $ git tag v1.0
    $ git tag
    v1.0

    $ git show v1.0
    commit 05193375f7a7c1295fd26c6388d81e188f405b0b
    Author: Thach Pham <contact@thachpham.com>
    Date: Thu Apr 23 02:20:50 2015 -0700

    Added a new tag

    diff --git a/tag.html b/tag.html
    new file mode 100644
    index 0000000..e69de29
```

##### Cách tạo Annotated Tag
``` git
    $ git tag -a v1.0-an -m "Ra mat phien ban 1.0"
    $ git show v1.0-an
    tag v1.0-an
    Tagger: Thach Pham <contact@thachpham.com>
    Date: Thu Apr 23 02:41:11 2015 -0700

    Ra mat phien ban 1.0

    commit d5a599e3385a8fc7a65958ed50bc8b54666b45ad
    Author: Thach Pham <contact@thachpham.com>
    Date: Thu Apr 23 02:40:31 2015 -0700

    Commit for Annotated Tag

    diff --git a/tag.html b/tag.html
    index e69de29..fea03c1 100644
    --- a/tag.html
    +++ b/tag.html
    @@ -0,0 +1 @@
    +Annotated Tag
```

##### Thêm tag cho các commit cũ

- Nếu bạn có rất nhiều commit trước đó mà cần gắn tag thì chỉ cần thêm mã checksum (hoặc một đoạn mã checksum) của lần commit đó.
- Để xem mã checksum của các lần commit trước đó thì bạn có thể sử dụng git log với tham số --pretty với giá trị oneline để lọc log nhé.

``` git
    $ git log --pretty=oneline
    d5a599e3385a8fc7a65958ed50bc8b54666b45ad Commit for Annotated Tag
    05193375f7a7c1295fd26c6388d81e188f405b0b Added a new tag
    435f642f951fbab1037fc2feef239ab26d6e6115 Added faq.html
    6904d5232bf90821068279311e205e3e1ff929f1 Initial commit
```

-  Khai báo một đoạn mã checksum của nó vào lệnh git tag như sau.

``` git
    $ git tag -a v0.0 c2ac50101 -m "Tag for inintial commit" 
```

#### Push Tag

- Mặc định lệnh git push sẽ không push các tag đã tạo lên repository mà bạn có thể dùng lệnh git push --tags để đẩy toàn bộ tag lên repository.

``` git
    $ git push --tags
```






