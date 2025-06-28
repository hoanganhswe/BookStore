--create database quanlicuahangsach;

CREATE TABLE Role (
                      roleID SERIAL PRIMARY KEY,
                      roleName VARCHAR(50) NOT NULL UNIQUE,
                      description TEXT
);

-- 2. Tạo bảng UserAccount (tài khoản người dùng)
CREATE TABLE UserAccount (
                             id SERIAL PRIMARY KEY,
                             username VARCHAR(50) NOT NULL UNIQUE,
                             password VARCHAR(255) NOT NULL,
                             role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'STAFF', 'CUSTOMER')),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Tạo bảng Staff (nhân viên)
CREATE TABLE Staff (
                       staffID SERIAL PRIMARY KEY,
                       staffName VARCHAR(100) NOT NULL,
                       position VARCHAR(50),
                       staffPhone VARCHAR(15),
                       email VARCHAR(100),
                       salary DECIMAL(12,2),
                       hire_date DATE,
                       user_id INTEGER REFERENCES UserAccount(id)
);

-- 4. Tạo bảng Customer (khách hàng)
CREATE TABLE Customer (
                          customerID SERIAL PRIMARY KEY,
                          customerName VARCHAR(100) NOT NULL,
                          customerPhone VARCHAR(15),
                          customerMail VARCHAR(100),
                          address TEXT,
                          date_of_birth DATE,
                          gender VARCHAR(10),
                          user_id INTEGER REFERENCES UserAccount(id)
);

-- 5. Tạo bảng Category (danh mục sách)
CREATE TABLE Category (
                          categoryID SERIAL PRIMARY KEY,
                          categoryName VARCHAR(100) NOT NULL,
                          description TEXT
);

-- 6. Tạo bảng Publisher (nhà xuất bản)
CREATE TABLE Publisher (
                           publisherID SERIAL PRIMARY KEY,
                           publisherName VARCHAR(100) NOT NULL,
                           address TEXT,
                           phone VARCHAR(15),
                           email VARCHAR(100)
);

-- 7. Tạo bảng Author (tác giả)
CREATE TABLE Author (
                        authorID SERIAL PRIMARY KEY,
                        authorName VARCHAR(100) NOT NULL,
                        biography TEXT,
                        date_of_birth DATE,
                        nationality VARCHAR(50)
);

-- 8. Tạo bảng Book (sách)
CREATE TABLE Book (
                      bookID SERIAL PRIMARY KEY,
                      bookName VARCHAR(200) NOT NULL,
                      bookAuthor VARCHAR(200), -- Tạm thời giữ để tương thích
                      yearOfPublication INTEGER,
                      bookPrice DECIMAL(10,2) NOT NULL,
                      category VARCHAR(50), -- Tạm thời giữ để tương thích
                      description TEXT,
                      stock_quantity INTEGER DEFAULT 0,
                      isbn VARCHAR(20),
                      pages INTEGER,
                      language VARCHAR(20) DEFAULT 'Vietnamese',
                      publisherID INTEGER REFERENCES Publisher(publisherID),
                      categoryID INTEGER REFERENCES Category(categoryID),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. Tạo bảng BookAuthor (liên kết sách và tác giả - nhiều nhiều)
CREATE TABLE BookAuthor (
                            bookID INTEGER REFERENCES Book(bookID) ON DELETE CASCADE,
                            authorID INTEGER REFERENCES Author(authorID) ON DELETE CASCADE,
                            PRIMARY KEY (bookID, authorID)
);

-- 10. Tạo bảng Supplier (nhà cung cấp)
CREATE TABLE Supplier (
                          supplierID SERIAL PRIMARY KEY,
                          supplierName VARCHAR(100) NOT NULL,
                          address TEXT,
                          phone VARCHAR(15),
                          email VARCHAR(100),
                          contact_person VARCHAR(100)
);

-- 11. Tạo bảng ImportOrder (đơn nhập hàng)
CREATE TABLE ImportOrder (
                             importOrderID SERIAL PRIMARY KEY,
                             supplierID INTEGER REFERENCES Supplier(supplierID),
                             staffID INTEGER REFERENCES Staff(staffID),
                             import_date DATE DEFAULT CURRENT_DATE,
                             total_amount DECIMAL(12,2),
                             status VARCHAR(20) DEFAULT 'PENDING',
                             notes TEXT
);

-- 12. Tạo bảng ImportOrderDetail (chi tiết đơn nhập hàng)
CREATE TABLE ImportOrderDetail (
                                   importOrderID INTEGER REFERENCES ImportOrder(importOrderID) ON DELETE CASCADE,
                                   bookID INTEGER REFERENCES Book(bookID),
                                   quantity INTEGER NOT NULL,
                                   unit_price DECIMAL(10,2) NOT NULL,
                                   subtotal DECIMAL(12,2) GENERATED ALWAYS AS (quantity * unit_price) STORED,
                                   PRIMARY KEY (importOrderID, bookID)
);

-- 13. Tạo bảng Order (đơn hàng)
CREATE TABLE "Order" (
                         orderID SERIAL PRIMARY KEY,
                         customerID INTEGER REFERENCES Customer(customerID),
                         orderDate DATE DEFAULT CURRENT_DATE,
                         orderStatus VARCHAR(20) DEFAULT 'PENDING',
                         total_amount DECIMAL(12,2),
                         shipping_address TEXT,
                         payment_method VARCHAR(20),
                         notes TEXT
);

-- 14. Tạo bảng OrderItem (chi tiết đơn hàng)
CREATE TABLE OrderItem (
                           orderID INTEGER REFERENCES "Order"(orderID) ON DELETE CASCADE,
                           bookID INTEGER REFERENCES Book(bookID),
                           quantity INTEGER NOT NULL,
                           price DECIMAL(10,2) NOT NULL,
                           subtotal DECIMAL(12,2) GENERATED ALWAYS AS (quantity * price) STORED,
                           PRIMARY KEY (orderID, bookID)
);

-- 15. Tạo bảng Cart (giỏ hàng)
CREATE TABLE Cart (
                      cartID SERIAL PRIMARY KEY,
                      customerID INTEGER REFERENCES Customer(customerID) ON DELETE CASCADE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 16. Tạo bảng CartItem (chi tiết giỏ hàng)
CREATE TABLE CartItem (
                          cartID INTEGER REFERENCES Cart(cartID) ON DELETE CASCADE,
                          bookID INTEGER REFERENCES Book(bookID),
                          quantity INTEGER NOT NULL DEFAULT 1,
                          added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (cartID, bookID)
);

-- 17. Tạo bảng PaymentMethod (phương thức thanh toán)
CREATE TABLE PaymentMethod (
                               methodName VARCHAR(50) PRIMARY KEY,
                               description TEXT,
                               is_active BOOLEAN DEFAULT TRUE
);

-- 18. Tạo bảng Invoice (hóa đơn)
CREATE TABLE Invoice (
                         invoiceID SERIAL PRIMARY KEY,
                         orderID INTEGER REFERENCES "Order"(orderID),
                         paymentMethod VARCHAR(50) REFERENCES PaymentMethod(methodName),
                         totalAmount DECIMAL(12,2) NOT NULL,
                         issueDate DATE DEFAULT CURRENT_DATE,
                         dueDate DATE,
                         staffID INTEGER REFERENCES Staff(staffID),
                         tax_amount DECIMAL(10,2) DEFAULT 0,
                         discount_amount DECIMAL(10,2) DEFAULT 0
);

-- 19. Tạo bảng Promotion (khuyến mãi)
CREATE TABLE Promotion (
                           promotionID SERIAL PRIMARY KEY,
                           promotionTitle VARCHAR(200) NOT NULL,
                           description TEXT,
                           discountPercent DECIMAL(5,2),
                           validFrom DATE NOT NULL,
                           validTo DATE NOT NULL,
                           is_active BOOLEAN DEFAULT TRUE,
                           min_order_amount DECIMAL(10,2) DEFAULT 0
);

-- 20. Tạo bảng Inventory (tồn kho)
CREATE TABLE Inventory (
                           inventoryID SERIAL PRIMARY KEY,
                           bookID INTEGER REFERENCES Book(bookID) ON DELETE CASCADE,
                           housing_stock_quantity INTEGER NOT NULL DEFAULT 0,
                           reserved_quantity INTEGER DEFAULT 0,
                           last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 21. Tạo bảng Review (đánh giá)
CREATE TABLE Review (
                        reviewID SERIAL PRIMARY KEY,
                        bookID INTEGER REFERENCES Book(bookID) ON DELETE CASCADE,
                        customerID INTEGER REFERENCES Customer(customerID),
                        rating INTEGER CHECK (rating >= 1 AND rating <= 5),
                        comment TEXT,
                        review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- 1. Insert Role
INSERT INTO Role (roleName, description) VALUES
                                             ('ADMIN', 'Quản trị viên hệ thống'),
                                             ('STAFF', 'Nhân viên cửa hàng'),
                                             ('CUSTOMER', 'Khách hàng');

-- 2. Insert UserAccount
INSERT INTO UserAccount (username, password, role) VALUES
                                                       ('admin', '$2b$10$abcdefghijklmnopqrstuvwxyz', 'ADMIN'),
                                                       ('staff01', '$2b$10$abcdefghijklmnopqrstuvwxyz', 'STAFF'),
                                                       ('staff02', '$2b$10$abcdefghijklmnopqrstuvwxyz', 'STAFF'),
                                                       ('customer01', '$2b$10$abcdefghijklmnopqrstuvwxyz', 'CUSTOMER'),
                                                       ('customer02', '$2b$10$abcdefghijklmnopqrstuvwxyz', 'CUSTOMER');

-- 3. Insert Staff
INSERT INTO Staff (staffName, position, staffPhone, email, salary, hire_date, user_id) VALUES
                                                                                           ('Nguyễn Văn A', 'Quản lý', '0901234567', 'admin@bookstore.com', 15000000, '2023-01-15', 1),
                                                                                           ('Trần Thị B', 'Nhân viên bán hàng', '0912345678', 'staff01@bookstore.com', 8000000, '2023-03-01', 2),
                                                                                           ('Lê Văn C', 'Thủ kho', '0923456789', 'staff02@bookstore.com', 9000000, '2023-02-15', 3);

-- 4. Insert Customer
INSERT INTO Customer (customerName, customerPhone, customerMail, address, date_of_birth, gender, user_id) VALUES
                                                                                                              ('Phạm Thị D', '0934567890', 'customer01@gmail.com', '123 Nguyễn Huệ, Q1, TP.HCM', '1990-05-15', 'Nữ', 4),
                                                                                                              ('Hoàng Văn E', '0945678901', 'customer02@gmail.com', '456 Lê Lợi, Q3, TP.HCM', '1985-12-20', 'Nam', 5);

-- 5. Insert Category
INSERT INTO Category (categoryName, description) VALUES
                                                     ('Văn học', 'Sách văn học trong và ngoài nước'),
                                                     ('Khoa học', 'Sách khoa học tự nhiên và ứng dụng'),
                                                     ('Kinh tế', 'Sách về kinh tế, tài chính, đầu tư'),
                                                     ('Công nghệ', 'Sách về công nghệ thông tin và lập trình'),
                                                     ('Tâm lý - Kỹ năng sống', 'Sách phát triển bản thân');

-- 6. Insert Publisher
INSERT INTO Publisher (publisherName, address, phone, email) VALUES
                                                                 ('NXB Trẻ', '161B Lý Chính Thắng, Q3, TP.HCM', '028-39316289', 'info@nxbtre.com.vn'),
                                                                 ('NXB Kim Đồng', '55 Quang Trung, Hoàn Kiếm, Hà Nội', '024-38253841', 'info@kimdong.com.vn'),
                                                                 ('NXB Thế Giới', '46 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '024-38220102', 'info@thegioipublishers.vn');

-- 7. Insert Author
INSERT INTO Author (authorName, biography, date_of_birth, nationality) VALUES
                                                                           ('Nguyễn Nhật Ánh', 'Nhà văn nổi tiếng với các tác phẩm thiếu nhi', '1955-05-07', 'Việt Nam'),
                                                                           ('Tô Hoài', 'Nhà văn với tác phẩm nổi tiếng Dế Mèn phiêu lưu ký', '1920-09-27', 'Việt Nam'),
                                                                           ('Dale Carnegie', 'Tác giả nổi tiếng với các sách kỹ năng sống', '1888-11-24', 'Mỹ');

-- 8. Insert Book
INSERT INTO Book (bookName, bookAuthor, yearOfPublication, bookPrice, category, description, stock_quantity, isbn, pages, publisherID, categoryID) VALUES
                                                                                                                                                       ('Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 'Nguyễn Nhật Ánh', 2010, 65000, 'Văn học', 'Tiểu thuyết về tuổi thơ miền quê', 50, '978-604-1-00001-1', 368, 1, 1),
                                                                                                                                                       ('Đắc Nhân Tâm', 'Dale Carnegie', 2018, 86000, 'Tâm lý - Kỹ năng sống', 'Sách kỹ năng giao tiếp nổi tiếng', 100, '978-604-1-00002-2', 320, 2, 5),
                                                                                                                                                       ('Dế Mèn Phiêu Lưu Ký', 'Tô Hoài', 2015, 45000, 'Văn học', 'Tác phẩm văn học thiếu nhi kinh điển', 75, '978-604-1-00003-3', 280, 2, 1);

-- 9. Insert BookAuthor
INSERT INTO BookAuthor (bookID, authorID) VALUES
                                              (1, 1),
                                              (2, 3),
                                              (3, 2);

-- 10. Insert Supplier
INSERT INTO Supplier (supplierName, address, phone, email, contact_person) VALUES
                                                                               ('Công ty Sách Miền Nam', '123 Pasteur, Q1, TP.HCM', '028-38291234', 'contact@sachmiennam.com', 'Nguyễn Văn F'),
                                                                               ('Nhà phân phối Fahasa', '60-62 Lê Lợi, Q1, TP.HCM', '028-38225798', 'supplier@fahasa.com', 'Trần Thị G');

-- 11. Insert PaymentMethod
INSERT INTO PaymentMethod (methodName, description) VALUES
                                                        ('Tiền mặt', 'Thanh toán bằng tiền mặt'),
                                                        ('Thẻ tín dụng', 'Thanh toán qua thẻ tín dụng'),
                                                        ('Chuyển khoản', 'Thanh toán qua chuyển khoản ngân hàng'),
                                                        ('Ví điện tử', 'Thanh toán qua ví điện tử');

-- 12. Insert Promotion
INSERT INTO Promotion (promotionTitle, description, discountPercent, validFrom, validTo, min_order_amount) VALUES
                                                                                                               ('Khuyến mãi tháng 6', 'Giảm 10% cho đơn hàng từ 200k', 10.00, '2025-06-01', '2025-06-30', 200000),
                                                                                                               ('Khuyến mãi hè', 'Giảm 15% cho đơn hàng từ 500k', 15.00, '2025-07-01', '2025-08-31', 500000);

-- 13. Insert Order
INSERT INTO "Order" (customerID, orderDate, orderStatus, total_amount, shipping_address, payment_method) VALUES
                                                                                                             (1, '2025-06-25', 'COMPLETED', 151000, '123 Nguyễn Huệ, Q1, TP.HCM', 'Tiền mặt'),
                                                                                                             (2, '2025-06-26', 'PENDING', 86000, '456 Lê Lợi, Q3, TP.HCM', 'Chuyển khoản');

-- 14. Insert OrderItem
INSERT INTO OrderItem (orderID, bookID, quantity, price) VALUES
                                                             (1, 1, 2, 65000),
                                                             (1, 3, 1, 45000), -- Tổng: 130000 + 21000 = 151000 (có thuế)
                                                             (2, 2, 1, 86000);

-- 15. Insert Cart
INSERT INTO Cart (customerID) VALUES
                                  (1),
                                  (2);

-- 16. Insert CartItem
INSERT INTO CartItem (cartID, bookID, quantity) VALUES
                                                    (1, 2, 1),
                                                    (2, 1, 2);

-- 17. Insert Invoice
INSERT INTO Invoice (orderID, paymentMethod, totalAmount, staffID, tax_amount) VALUES
                                                                                   (1, 'Tiền mặt', 151000, 2, 21000),
                                                                                   (2, 'Chuyển khoản', 86000, 2, 0);

-- 18. Insert Inventory
INSERT INTO Inventory (bookID, housing_stock_quantity) VALUES
                                                           (1, 48), -- 50 - 2 đã bán
                                                           (2, 99), -- 100 - 1 đã bán
                                                           (3, 74); -- 75 - 1 đã bán

-- 19. Insert Review
INSERT INTO Review (bookID, customerID, rating, comment) VALUES
                                                             (1, 1, 5, 'Sách rất hay, gợi nhớ tuổi thơ'),
                                                             (2, 2, 4, 'Nội dung bổ ích cho việc giao tiếp');

select * from Role;
