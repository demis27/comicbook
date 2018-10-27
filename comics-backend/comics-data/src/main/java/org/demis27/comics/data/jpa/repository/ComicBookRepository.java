package org.demis27.comics.data.jpa.repository;

import org.demis27.comics.data.jpa.entity.ComicBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("comicBookRepository")
public interface ComicBookRepository extends JpaRepository<ComicBook, String> {

}
