create table author (
    id int AUTO_INCREMENT,
    name varchar(30) Unique NOT NULL,
    country varchar(30) NOT NULL,
    age int NOT NULL,
    email varchar(40),
    constraint pk_author PRIMARY KEY (id)
);

create table publisher (
    id int AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    country varchar(20) NOT NULL,
    constraint pk_publisher PRIMARY KEY (id)
);

create table book (
    isbn bigint,
    title varchar(50) NOT NULL,
    author_id int NOT NULL,
    publisher_id int NOT NULL,
    year int NOT NULL,
    constraint pk_book PRIMARY KEY (isbn)
);

create table admin (
    id int AUTO_INCREMENT,
    username varchar(30) Unique NOT NULL,
    password varchar(30) NOT NULL,
    constraint pk_admin PRIMARY KEY (id)
);