insert into user
values (1001, sysdate(), 'Alain MUCYO');
insert into user
values (2002, sysdate(), 'Alain');
insert into user
values (3003, sysdate(), 'MUCYO');

insert into post
values (1100, 'This is the awesome post you have been dreaming about', 1001);
insert into post
values (1101, 'This is my new post for you', 2002);
insert into post
values (1102, 'Another awesome post that I crafted by my hands', 3003);
insert into post
values (1103, 'A new post post that I crafted by my hands', 3003);