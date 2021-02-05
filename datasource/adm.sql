create Table user_login_details(

first_Name VARCHAR(20) NOT NULL ,
last_Name VARCHAR(10) NOT NULL,
email VARCHAR(30) NOT NULL ,
user_password  VARCHAR(20) NOT NULL,
gender VARCHAR(10) NOT NULL,
primary key (email)

);

drop table user_login_details

insert into user_login_details values ("deepika", "pradhan", "deepika@sa.com","future@123", "F");
update user_login_details set user_password = "123" where email = 'deepika@sa.com';

select * from user_login_details


create Table category(
	category_name VARCHAR(30) NOT NULL,
    category_id VARCHAR(10) NOT NULL
)

insert into category values ("Mobiles" , "MB0000");
insert into category values ("Home Appliences" , "HA0000");
insert into category values ("Laptops" , "LP0000");
insert into category values ("Television" , "TV0000");

select * from category;