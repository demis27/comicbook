package org.demis27.comics.data.jpa.repository;

import org.demis27.comics.data.jpa.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, String> {
}
