# comicbook

## next tasks

* Import / Export
* Add Series issues - foreign key
* Add some data
* make an executable
* Test Heroku

## ci

https://travis-ci.org/demis27/comicbook

## Repository

https://github.com/demis27/comicbook

## docker

install docker
post-install docker

$sudo addgroup --system docker
$sudo adduser $USER docker
$newgrp docker

## Insert data

=CONCATENATE("insert into comic_book (comic_book_id, title, isbn  ) values (uuid_in(md5(random()::text || clock_timestamp()::text)::cstring), '"; C2; "', '";G2;"');")

## Data structure

Serie:
* Name
* issues
* finish
* comments
* style

Issue:
* number
* comicbook

Comicbook:
* title
* isbn/ean
* authors / roles
* editions
* (have, read)
* (note)
* note
* (date d'achat)
* (edition)
* (état)
* (prix)
* prix
* (dédicace)
* (comment)
* (preter)

Author:
* name
* biographie
* comments

Editions:
* original 
* date
* editor
