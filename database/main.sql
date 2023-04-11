create database rest_api_app_db;
use rest_api_app_db;

create table `User`(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email varchar(100),
    nickname varchar(100),
    password varchar(100),
    last_location varchar(100)
);

create table `Person`(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100), 
    surname varchar(100),
    age int
);

select * from person;
insert into person(name, surname, age) values('Dmitry', 'Orlov', 22);