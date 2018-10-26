create table if not exists comic_book (
	 comic_book_id 					varchar(64)
	,title							varchar(256)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null
	, constraint comic_book_pk primary key (comic_book_id)
);