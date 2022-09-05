alter table book
    add constraint fk_book1 foreign key (author_id) references author (id),
    add constraint fk_book2 foreign key (publisher_id) references publisher (id);