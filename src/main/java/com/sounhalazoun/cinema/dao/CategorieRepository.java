package com.sounhalazoun.cinema.dao;

import com.sounhalazoun.cinema.entities.Categorie;
import com.sounhalazoun.cinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
