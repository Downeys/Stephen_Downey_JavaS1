create schema if not exists comment_test;
use comment_test;

create table if not exists comment (
	comment_id int not null auto_increment primary key,
    post_id int not null,
    create_date date not null,
    commenter_name varchar(50) not null,
    comment varchar(255)
);
