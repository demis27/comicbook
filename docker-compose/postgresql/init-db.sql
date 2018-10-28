create table comic_book (
	 comic_book_id 					varchar(37)
	,title							varchar(256)
	,isbn							varchar(17)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null	
	, constraint comic_book_pk primary key (comic_book_id)
);

create table person (
	 person_id 						varchar(37)
	,firstname						varchar(64)
	,lastname						varchar(64)
	,middlename						varchar(64)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null	
	, constraint person_pk primary key (person_id)
);

create table if not exists series (
     series_id                      varchar(37)
    ,title                          varchar(256)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null
	, constraint series_pk primary key (series_id)
);

create table if not exists series_issue (
     series_issue_id                varchar(37)
    ,series_id                      varchar(37)
    ,comic_book_id                  varchar(37)
    ,number_in_series               int
    ,label                          varchar(4)
	,creation_date               	timestamp without time zone default current_date not null
	,modification_date 				timestamp without time zone default current_date not null
	, constraint series_issue_pk primary key (series_issue_id)
    , foreign key (series_id) references series(series_id)
    , foreign key (comic_book_id) references comic_book(comic_book_id)
);
