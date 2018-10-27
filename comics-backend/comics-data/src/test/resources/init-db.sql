create table if not exists comic_book (
	 comic_book_id 					varchar(37)
	,title							varchar(256)
	,isbn							varchar(13)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null
	, constraint comic_book_pk primary key (comic_book_id)
);

create table if not exists person (
	 person_id 						varchar(37)
	,firstname						varchar(32)
	,lastname						varchar(32)
	,middlename						varchar(32)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null
	, constraint person_pk primary key (person_id)
);
