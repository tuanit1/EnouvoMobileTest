- [1. SSH là gì?](#1-ssh-là-gì)
- [2. Lợi ích sử dụng SSH đối với Git](#2-lợi-ích-sử-dụng-ssh-đối-với-git)
- [3. Cơ chế làm việc](#3-cơ-chế-làm-việc)
- [4. Thiết lập SSH Key](#4-thiết-lập-ssh-key)
    - [Kiểm tra ssh-key tồn tại](#kiểm-tra-ssh-key-tồn-tại)
    - [Tạo mới một SSH Key](#tạo-mới-một-ssh-key)
    - [Thêm key vào ssh-agent](#thêm-key-vào-ssh-agent)
    - [Thiết lập Git Server (Gitlab hoặc Github)](#thiết-lập-git-server-gitlab-hoặc-github)

## 1. SSH là gì?
- SSH là một chương trình tương tác giữa máy chủ và máy khách có sử dụng cơ chế mã hoá đủ mạnh nhằm ngăn chặn các hiện tượng nghe trộm, đánh cắp thông tin trên đường truyền.
- Sử dụng SSH là biện pháp hữu hiệu bảo mật dữ liệu trên đường truyền từ hệ thống này đến hệ thống khác.

## 2. Lợi ích sử dụng SSH đối với Git
- Bảo mật các kết nối của máy tính với server.
- Không phải nhập mật khẩu github hoặc gitlab mỗi lần pull hoặc push code.

## 3. Cơ chế làm việc
- Bạn sẽ có 2 key: **public key** và **private key**. Bạn sẽ gửi public key của mình cho git server của bạn (gitlab hay github chẳng hạn).
- Xong **ssh-agent** sẽ làm tất cả những việc còn lại cho bạn. Mỗi lần bạn push, **ssh-agent** sẽ tự gửi kèm các thông tin chứng thực đi, github sẽ nhận diện ra bạn, và bạn không cần phải nhập mật khẩu nữa.

## 4. Thiết lập SSH Key
#### Kiểm tra ssh-key tồn tại
``` bash
    $ ls -al ~/.ssh
    id_rsa
    id_rsa.pub
    id_dsa.pub
    id_ecdsa.pub
    id_ed25519.pub
```
- **public-key** sẽ có đuôi .pub
- **private-key** không có đuôi .pub
#### Tạo mới một SSH Key
``` sh
    ssh-keygen -t rsa -b 4096 -C "email@example.com" 
    ssh-keygen -t rsa
```
#### Thêm key vào ssh-agent
``` sh
    ssh-add ~/.ssh/id_rsa
```
#### Thiết lập Git Server (Gitlab hoặc Github)
- Copy ssh key vào clipboard
``` sh
    pbcopy < ~/.ssh/id_rsa.pub 
    //or
    cat ~/.ssh/id_rsa.pub
```
- Đối với GitHub
    - Truy cập vào địa chỉ: https://github.com/settings/profile
    - Click chọn SSH and GPG keys > New SSH Key
    
    ![overview](https://blog.nguyenary.dev/wp-content/uploads/2020/12/add_new_ssh_key-768x513.png.webp)

- Đối với GitLab
  - Bạn truy cập vào: https://gitlab.com/-/profile/keys

  ![overview](https://blog.nguyenary.dev/wp-content/uploads/2020/12/add_ssh_key_gitlab-700x410.png.webp)