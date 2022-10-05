- [1. Git follow là gì?](#1-git-follow-là-gì)
- [2. Các branch chính](#2-các-branch-chính)
- [3. Giải thích chi tiết các nhánh](#3-giải-thích-chi-tiết-các-nhánh)
  - [master](#master)
  - [develop](#develop)
  - [feature](#feature)
  - [release](#release)
  - [hotfix](#hotfix)

## 1. Git follow là gì?
- Git flow là tập hợp các quy ước bao gồm cách chia nhánh và merge nhánh vào khi hoàn thành một tập hợp tính năng hoặc fix.
- Git Flow đưa ra các quy ước để triển khai công việc. Nó được tổng kết qua quá trình làm việc thực tiễn của nhiều team trên thế giới. Mục đích là các nhóm công việc triển khai song song nhưng không ảnh hưởng tới nhau

## 2. Các branch chính
- **master**: là branch tồn tại xuyên suốt quá vòng đời của phần mềm được tạo mặc định trong Git khi ta tạo repository.
- **develop**: là nơi các develop phát triển chính branch luôn tồn tại song song với master.
- **feature**: là nhánh được tách từ develop nhằm mục đích xây dựng các tính năng riêng mà không phụ thuộc vào nhau.
- **release**: là nhánh tách từ develop để kiểm tra và fix bug chuẩn bị cho việc ra mắt sản phẩm.
- **hotfix**: là nhánh tách từ master để fix gấp những bug còn tồn đọng mà trên release chưa xử lý hết.

![overview](https://static.wixstatic.com/media/20d819_4dc550871b9c4c4d8936f0a766536946~mv2.png/v1/fill/w_1175,h_440,al_c,q_90,usm_0.66_1.00_0.01,enc_auto/20d819_4dc550871b9c4c4d8936f0a766536946~mv2.png)

## 3. Giải thích chi tiết các nhánh

### master
- Đây là branch chính của cả dự án chỉ có thể merge vào từ **branch release** chứ không được code trực tiếp trên branch này. Sản phẩm được đưa lên production sẽ được lấy ở đây nên code được merge vào **master** phải được quản lý và xử lý cẩn thận nhất có thể.

### develop
- **Branch develop** là branch trung tâm cho việc phát triển. 

- Mỗi khi có tính năng mới cần xây dựng thì **branch feature** sẽ được tách từ **branch develop** để phát triển
Mỗi khi chuẩn bị release phiên bản mới thì **branch release** sẽ được tách từ **branch develop** để test

![](https://static.wixstatic.com/media/20d819_11ecdfc37c22405bbf8cc0c472711651~mv2.png/v1/fill/w_450,h_678,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/20d819_11ecdfc37c22405bbf8cc0c472711651~mv2.png)

### feature

- Các **branch feature** được sử dụng để phát triển các tính năng mới cho bản release sắp tới hoặc trong tương lai xa
- **Branch feature** cuối cùng sẽ hợp nhất trở lại **branch develop** (khi thêm tính năng mới vào bản release sắp tới) hoặc bị loại bỏ (trong trường hợp thử nghiệm đáng thất vọng).
- Ngoài các tính năng chính thì còn có thể tách thành cách tính năng nhỏ hơn để thuận tiện cho việc phát triển tùy vào cấu trúc cũng như độ phức tạp của dự án

    ![](https://static.wixstatic.com/media/20d819_58a1e2c61def41d09b78e4bee7badeca~mv2.png/v1/fill/w_319,h_857,al_c,lg_1,q_85,enc_auto/20d819_58a1e2c61def41d09b78e4bee7badeca~mv2.png)

### release
- **Branch release** là branch dùng để release sản phẩm như đúng tên gọi của nó. Khi khách hàng cần release một số tính năng thì một branch sẽ được tách từ **branch develop** ra với tên theo cấu trúc **"release/v.1.0.1"** . Sau đó sẽ test và fix bug trên **branch release**, khi xong sẽ merge vào **branch master** để đẩy sản phẩm lên và merge vào **branch develop** để tránh gặp lại các bug đã được test và fix

### hotfix
- Có 2 trường hợp thường xảy ra:
  - Có những bug mà trong quá trình sử dụng thực tế người dùng phát hiện ra mà QA không test được

  - Đôi lúc vì một số lý do khách hàng muốn ra mắt ngay tính năng mới đang phát triển trên develop. Và tất nhiên khi đẩy trực tiếp như thế sẽ xuất hiện một lỗi nghiêm trọng trong phiên bản release. 

- Vì code trên master phải là code release được nên nếu có bugs nó phải được ưu tiên sửa chữa ngay. Từ **master branch hotfix** sẽ được tạo và xử lý khi xong sẽ lại được merge vào **master** và **develop**..

![](https://static.wixstatic.com/media/20d819_0feed90969d447d9927b0c56eb6fbe35~mv2.png)