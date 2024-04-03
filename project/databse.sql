create database venuedatabase;
use venuedatabase;
create table customerlogin( name varchar(100) not null,
email varchar(50) not null, password varchar(255) not null);
insert into customerlogin(name,email, password) values('admin','asd', 'admin123');



select* from customerlogin;
select * from customersignup;
