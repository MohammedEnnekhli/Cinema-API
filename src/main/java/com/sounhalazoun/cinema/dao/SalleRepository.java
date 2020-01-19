package com.sounhalazoun.cinema.dao;

import com.sounhalazoun.cinema.entities.Film;
import com.sounhalazoun.cinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
