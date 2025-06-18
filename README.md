# Group14_OOP_N02_term3_2025 

## Thành Viên:
Lê Xuân Hoàng, Nguyễn Tiến Hoàng Vũ, Nguyễn Anh Quân

## Tiêu Đề :

Quản lý thư viện sách

## ReadMe giới thiệu project:
Link: [https://github.com/hoag-le/Group14_OOP_N02_term3_2025](https://github.com/hoag-le/Group14_OOP_N02_term3_2025/tree/main/ReadMe)

## Đối Tượng:
- Sách (Book)
- Thành viên (Member)
- Thư viện (Library)  
=======


**Xây dựng ứng dụng Quản lý Thư viện Sách.**
-   **Giao diện:** Java Spring Boot.
-   **Có chức năng quản lý Sách (Book):**
    +   Thêm, sửa, xóa thông tin Sách.
    +   Liệt kê thông tin về Sách, có thể lọc ra các Sách theo Tên sách, Tác giả, Thể loại, Mã ISBN, Nhà xuất bản, Năm xuất bản,...
-   **Có chức năng quản lý Thành viên (Member):**
    +   Thêm, sửa, xóa thông tin Thành viên.
    +   Liệt kê thông tin về Thành viên, có thể lọc ra các Thành viên theo Tên, Mã thành viên, ...
-   **Có chức năng quản lý việc Mượn/Trả Sách:**
    +   Cho phép Thành viên mượn Sách (tạo Phiếu Mượn).
    +   Ghi nhận việc Thành viên trả Sách (cập nhật Phiếu Mượn).
    +   Liệt kê các Sách đang được mượn, Sách đã trả, Sách quá hạn (nếu có).
-   **Dữ liệu được lưu trữ xuống file nhị phân:**
    +   Cần tạo các lớp liên quan đến **Sách (Book)**, **Thành viên (Member)**, và **Phiếu Mượn/Trả (BorrowingRecord/Loan)** để đọc, ghi dữ liệu xuống một hay nhiều file nhị phân. Lớp `Library` sẽ quản lý việc này.
-   **Khi làm việc với dữ liệu trong bộ nhớ:** Dữ liệu cần được lưu trữ dưới dạng các Collection tùy chọn như `ArrayList`, `LinkedList`, `HashMap`, `HashSet`, ... để quản lý danh sách Sách, Thành viên, và các giao dịch mượn/trả.
-   **Mở rộng (Tùy chọn):** Sinh viên có thể thêm các chức năng vào chương trình để ứng dụng phong phú hơn bằng cách thêm các nghiệp vụ cho bài toán (ví dụ: tìm kiếm nâng cao, thống kê báo cáo, quản lý đặt trước sách, thông báo quá hạn, phân quyền người dùng,...).

### Sơ đồ Lớp (Class Diagram)
**Dự kiến các lớp chính:**
*   `Book`: Lưu trữ thông tin sách (mã sách, tên sách, tác giả, nhà xuất bản, năm XB, thể loại, số lượng, ISBN,...).
*   `Member`: Lưu trữ thông tin thành viên (mã thành viên, họ tên, ngày sinh, địa chỉ, SĐT,...).
*   `BorrowingRecord` (hoặc `Loan`): Lưu trữ thông tin mượn trả (mã phiếu, mã thành viên, mã sách, ngày mượn, ngày hẹn trả, ngày trả thực tế, trạng thái).
*   `Library`: Lớp quản lý chính, chứa danh sách các `Book`, `Member`, `BorrowingRecord`; cung cấp các phương thức nghiệp vụ (thêm/sửa/xóa sách/thành viên, cho mượn, nhận trả, tìm kiếm, lưu/tải dữ liệu).
*   `Author` (Tùy chọn): Nếu muốn quản lý thông tin tác giả chi tiết.
*   `Category` (Tùy chọn): Nếu muốn quản lý thông tin thể loại chi tiết.
*   `DataManager` (Tùy chọn): Lớp chuyên trách việc đọc/ghi dữ liệu từ file nhị phân.

![Class Diagram](/docs/ClassDiagram.png)

### Sơ đồ Hành vi (Behavioural Diagrams)

*(Các sơ đồ hành vi chi tiết sẽ được thiết kế và cập nhật hình ảnh hoặc liên kết tại đây sau khi hoàn thiện)*

1.  **Sơ đồ Tuần tự (Sequence Diagram):**
    *   Mô tả tương tác giữa các đối tượng trong một kịch bản cụ thể.
    *   Ví dụ: Kịch bản "Thành viên mượn sách", "Thêm sách mới vào thư viện".
    *   *[Placeholder cho hình ảnh/liên kết Sơ đồ Tuần tự]*

2.  **Sơ đồ Hoạt động (Activity Diagram):**
    *   Mô tả luồng công việc hoặc quy trình của một chức năng.
    *   Ví dụ: Quy trình "Xử lý yêu cầu mượn sách", "Quy trình đăng ký thành viên mới".
    *   *[Placeholder cho hình ảnh/liên kết Sơ đồ Hoạt động]*

3.  **Sơ đồ Ca sử dụng (Use Case Diagram):**
    *   Mô tả các chức năng chính của hệ thống từ góc độ người dùng (actors).
    *   Ví dụ: Actor "Thủ thư" có các use case: "Quản lý Sách", "Quản lý Thành viên", "Quản lý Mượn/Trả". Actor "Thành viên" có use case: "Tìm kiếm Sách", "Xem thông tin cá nhân".
    *   *[Placeholder cho hình ảnh/liên kết Sơ đồ Ca sử dụng]*
    *   
![Behavioural Diagrams](/docs/SequenceDiagram.jpg)

### CRUD
1. Sách (Book)
create_book(): Thêm sách mới

* get_books(): Lấy danh sách sách

* update_book(book_id): Cập nhật thông tin sách

* delete_book(book_id): Xóa sách

2. Thành viên (Member)
create_member()

* get_members()

* update_member(member_id)

* delete_member(member_id)

3. Thư viện (Library)
create_library()

* get_libraries()

* update_library(library_id)

* delete_library(library_id)

### Lưu đồ thuật toán 

![Lưu đồ chức năng chính](docs/luu_do.png) 

### Phương thức 
 
**1. Phương thức: kiemTraSachGanDenHanTra (Nguyễn Tiến Hoàng Vũ)**

- Miêu tả phương thức:

* Tên phương thức: kiemTraSachGanDenHanTra

* Mục đích: Kiểm tra xem phiếu mượn có nằm trong khoảng thời gian gần đến hạn trả hay không.

* Tham số đầu vào:

* phieuMuon: Đối tượng PhieuMuon

* soNgayCanhBao: số nguyên – khoảng cảnh báo (ví dụ: 3 ngày)

* Giá trị trả về: true nếu ngày trả nằm trong khoảng từ hiện tại đến (hiện tại + soNgayCanhBao), ngược lại false.
