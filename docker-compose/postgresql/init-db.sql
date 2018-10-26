create table comic_book (
	 comic_book_id 					varchar(64)
	,title							varchar(256)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null	
	, constraint comic_book_pk primary key (comic_book_id)
);

create table person (
	 id 							varchar(32)
	,firstname						varchar(32)
	,lastname						varchar(32)
	,middlename						varchar(32)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null	
);

