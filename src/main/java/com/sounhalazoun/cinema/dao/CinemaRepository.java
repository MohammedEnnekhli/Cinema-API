package com.sounhalazoun.cinema.dao;

import com.sounhalazoun.cinema.entities.Cinema;
import com.sounhalazoun.cinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CinemaRepository extends JpaRepository<Cinema,Long> {
}
