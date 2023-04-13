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
    age int CHECK(age > 0),
    user_id int UNIQUE,
    FOREIGN KEY(user_id) REFERENCES `User`(id) ON DELETE SET NULL
);

insert into person(name, surname, age, user_id) values('Dmitry', 'Orlov', 22, 1);
insert into user(email, nickname, password) values('dimas_orlov99@mail.ru', 'dim4eSS', 'QwertY_123');

select * from user;
select * from person;

delete from person where id=4;


insert into user(email, nickname, password) values('maxim@mail.ru', 'Maxim', 'Shadow_Fiend');





 