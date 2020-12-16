insert into author (author_name, description, id) values ('Yazar1', 'Yazar1 Açıklama', 1);
insert into author (author_name, description, id) values ('Yazar2', 'Yazar2 Açıklama', 2);
insert into author (author_name, description, id) values ('Yazar3', 'Yazar3 Açıklama', 3);
insert into author (author_name, description, id) values ('Yazar4', 'Yazar4 Açıklama', 4);


insert into publisher ( publisher_name,description, id) values ('YayınEvi1', 'YayınEvi1 Açıklama', 1);
insert into publisher ( publisher_name,description, id) values ('YayınEvi2', 'YayınEvi2 Açıklama', 2);
insert into publisher ( publisher_name,description, id) values ('YayınEvi3', 'YayınEvi3 Açıklama', 3);
insert into publisher ( publisher_name,description, id) values ('YayınEvi4', 'YayınEvi4 Açıklama', 4);


insert into book (book_name, description, isbn, publisher_id, seri_name, sub_name, id) values ('Kitap1', 'Kitap1 Açıklama', 'Kitap1 isbn', 1, 'Kitap1 seriname', 'Kitap1 alt isim', 1);
insert into author_book (book_id, author_id) values (1, 1);
insert into book (book_name, description, isbn, publisher_id, seri_name, sub_name, id) values ('Kitap2', 'Kitap2 Açıklama', 'Kitap2 isbn', 2, 'Kitap1 seriname', 'Kitap2 alt isim', 2);
insert into author_book (book_id, author_id) values (2, 2);
insert into book (book_name, description, isbn, publisher_id, seri_name, sub_name, id) values ('Kitap3', 'Kitap3 Açıklama', 'Kitap3 isbn', 3, 'Kitap1 seriname', 'Kitap3 alt isim', 3);
insert into author_book (book_id, author_id) values (3, 3);
insert into book (book_name, description, isbn, publisher_id, seri_name, sub_name, id) values ('Kitap4', 'Kitap4 Açıklama', 'Kitap4 isbn', 4, 'Kitap1 seriname', 'Kitap4 alt isim', 4);
insert into author_book (book_id, author_id) values (4, 4);
