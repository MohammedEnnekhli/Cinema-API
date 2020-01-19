package com.sounhalazoun.cinema.dao;

import com.sounhalazoun.cinema.entities.Film;
import com.sounhalazoun.cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface SeanceRepository extends JpaRepository<Seance,Long> {
}
