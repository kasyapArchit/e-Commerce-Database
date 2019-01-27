drop database ecomm;
create database ecomm;
use ecomm;
create table User (
	mobile_no bigint not null primary key,
	password varchar(15),

	fname varchar(30),
	lname varchar(30),

	address_line_1 varchar(30),
	address_line_2 varchar(30),
	address_line_3 varchar(30)
);

create table Customer (
    mobile_no bigint not null primary key,
    foreign key (mobile_no) references User (mobile_no)
);

create table Seller (
    mobile_no bigint not null primary key,
    approved boolean default 0,
    gst_no bigint not null unique,

    foreign key (mobile_no) references User (mobile_no)
);

create table Complaint (
    complaint_id bigint primary key auto_increment,
    comp_subject text not null,
    comp text not null,
    stat boolean default 0,
    mobile_no bigint not null,

    foreign key (mobile_no) references User (mobile_no)
);

create table Product (
    product_id bigint primary key auto_increment,
    seller_mobile_no bigint not null,
    product_name varchar (30) not null,
    price float not null,
    offer float default 0,
    available_quantity int not null,
    
    foreign key (seller_mobile_no) references Seller (mobile_no)
);

create table TOrder (
    order_id bigint primary key auto_increment,
    customer_mobile_no bigint not null,
    total_amount float not null,
    delivery_address varchar(100),

    foreign key (customer_mobile_no) references Customer (mobile_no)
);

create table OrderItem (
    order_item_id bigint primary key auto_increment,
    order_id bigint not null,
    product_id bigint not null,
    amount float not null,
    quantity int default 1,
    stat int not null, -- 0=>not yet deleivered,1=>deleivered,2=>return period over,3=>returned 

    foreign key (product_id) references Product (product_id),
    foreign key (order_id) references TOrder (order_id)
);


create table Review (
    product_id bigint not null primary key,
    review_text varchar (200),

    foreign key (product_id) references Product (product_id)
);

create table DeliveryAgent (
    mobile_no bigint not null primary key,
    fname varchar(30) not null,
	lname varchar(30) not null
);

create table Delivery (
    delivery_id bigint primary key auto_increment,
    mobile_no bigint not null,
    order_item_id bigint not null,
    stat int not null, -- 0 => not delivered, 1 => delivered, 3=>refund

    foreign key (mobile_no) references DeliveryAgent (mobile_no),
    foreign key (order_item_id) references OrderItem (order_item_id)    
);

create table TReturns (
    order_item_id bigint not null,
    amount float default 0,
    stat int not null, -- 1=>refund, 2=>replacement

    foreign key (order_item_id) references OrderItem(order_item_id),
    primary key (order_item_id)
);

insert into User (mobile_no, password, fname, lname, address_line_1, address_line_2)
values
(9885431454, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431451, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431452, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431453, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431455, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431456, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),
(9885431457, "password", "random", "gupta", "26, IIITB", "Ecity, Bangalore"),

(7885431451, "password", "trader1", "reddy", "1, PK", "JH, Hyd"),
(7885431452, "password", "trader2", "reddy", "2, PK", "JH, Hyd"),
(7885431453, "password", "trader3", "reddy", "3, PK", "JH, Hyd"),
(7885431454, "password", "trader4", "reddy", "4, PK", "JH, Hyd"),
(7885431455, "password", "trader4", "reddy", "4, PK", "JH, Hyd"),
(7885431456, "password", "trader4", "reddy", "4, PK", "JH, Hyd"),
(7885431457, "password", "trader4", "reddy", "4, PK", "JH, Hyd")
;

insert into Customer (mobile_no)
values
(9885431454),
(9885431451),
(9885431452),
(9885431453),
(9885431455),
(9885431456),
(9885431457)
;

insert into Seller (mobile_no, gst_no)
values
(7885431451, 1234567891),
(7885431452, 1234567892),
(7885431453, 1234567893),
(7885431454, 1234567894),
(7885431455, 1234567895),
(7885431456, 1234567896),
(7885431457, 1234567897)
;

insert into Product (product_id, seller_mobile_no, product_name, price, offer, available_quantity)
values
(1, 7885431451, "REDMI N4", 12000, 2000, 25),
(2, 7885431452, "MOTO GMAX", 17000, 1000, 13),
(3, 7885431453, "IPHONE X", 80000, 0, 5),
(4, 7885431454, "SAMSUNG S6", 55000, 0, 9),
(5, 7885431455, "Dell XPS13", 85000, 3000, 17),
(6, 7885431456, "MacBook Pro", 180000, 15000, 12),
(7, 7885431457, "Lenovo Ideapad", 45000, 5000, 23)
; 

insert into TOrder(order_id, customer_mobile_no, total_amount, delivery_address)
values
(1, 9885431451, 26000, "Bangalore"),
(2, 9885431452, 80000, "Bangalore"),
(3, 9885431453, 55000, "Bangalore"),
(4, 9885431454, 180000, "Bangalore")
;


insert into OrderItem(order_item_id, order_id, product_id, amount, stat)
values
(1, 1, 1, 10000, 0),
(2, 1, 2, 16000, 3),
(3, 2, 3, 80000, 2),
(4, 3, 4, 55000, 0),
(5, 4, 6, 180000, 1)
;

insert into DeliveryAgent(mobile_no, fname, lname)
values
(9876543210, "Hello", "World"),
(9876543211, "Hello1", "World"),
(9876543212, "Hello2", "World"),
(9876543213, "Hello3", "World"),
(9876543214, "Hello4", "World"),
(9876543215, "Hello5", "World")
;

insert into Delivery(delivery_id, mobile_no, order_item_id, stat)
values
(1, '9876543210', 1, 0),
(2, '9876543211', 2, 1),
(3, '9876543212', 3, 1),
(4, '9876543213', 4, 0),
(5, '9876543214', 5, 1)
;

insert into TReturns(order_item_id, amount, stat)
values
(2, 16000, 1)
;