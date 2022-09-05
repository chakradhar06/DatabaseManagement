drop database projectdb;

create database projectdb;

use projectdb;

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

alter table book
    add constraint fk_book1 foreign key (author_id) references author (id),
    add constraint fk_book2 foreign key (publisher_id) references publisher (id);

insert into author (name, country, age, email) values  /* id */
('J K Rowling', 'British', '56', 'info@jkrowling.com'),
('Derek Landy', 'Irish', '47', NULL),
('J R R Tolkien', 'British', '81', NULL);

insert into publisher (name, country) values /* id */
('Bloomsbury', 'UK'),
('HarperCollins', 'USA'),
('George Allen & Unwin', 'Australia');

insert into book (isbn, title, author_id, publisher_id, year) values 
('9780747532743', 'Harry Potter and the Philosophers Stone', '01', '01', '1997'),
('9780747538486', 'Harry Potter and the Chamber of Secrets', '01', '01', '1998'),
('9780439136365', 'Harry Potter and the Prisoner of Azkaban', '01', '01', '1999'),
('9780747546245', 'Harry Potter and the Goblet of Fire', '01', '01', '2000'),
('9780747551003', 'Harry Potter and the Order of the Phoenix', '01', '01', '2003'),
('9780439784542', 'Harry Potter and the Half-Blood Prince', '01', '01', '2005'),
('9780545010221', 'Harry Potter and the Deathly Hallows', '01', '01', '2007'),
('9780007257041', 'Skulduggery Pleasant : Playing with Fire', '02', '02', '2008'),
('9780008248802', 'Skulduggery Pleasant : The Faceless Ones', '02', '02', '2009'),
('9780007325979', 'Skulduggery Pleasant : Dark Days', '02', '02', '2010'),
('9780007326013', 'Skulduggery Pleasant : Mortal Coil', '02', '02', '2010'),
('9780618042203', 'Lord of the Rings : The Ring Sets Out', '03', '03', '1976'),
('9780618042210', 'Lord of the Rings : The Ring Goes South', '03', '03', '1982'),
('9780261102200', 'Lord of the Rings : The Treason of Isengard', '03', '03', '1989');

insert into admin (username, password) values  /* id */
('Chakri', 'abcd');