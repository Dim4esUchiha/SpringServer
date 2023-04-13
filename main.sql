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


create table Tag(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tag_name varchar(30) unique
);

insert into Tag(tag_name) values("Cinema");
select * from tag;

create table Person_Tag(
	person_id int,
    tag_id int,
    foreign key (person_id) references person(id),
    foreign key (tag_id) references tag(id),
    primary key (person_id, tag_id)
);

create table Data(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file_path varchar(100),
    file_type varchar(30),
    person_id int,
    foreign key (person_id) references person(id)
);

create table chat(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    chat_name varchar(50)
);

create table message(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date datetime,
    info varchar(255),
    chat_id int,
    foreign key (chat_id) references chat(id)
);

create table user_chat(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    chat_id int,
    foreign key (user_id) references user(id),
    foreign key (chat_id) references chat(id)
);

create table role(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name varchar(30), -- enum
    person_id int ,
    foreign key(person_id) references person(id)
);

create table contact(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date_add datetime,
    user_id int,
    foreign key (user_id) references user(id)
);






drop table data;


drop table person_tag;
drop table tag;
drop table person;
drop table user;






 