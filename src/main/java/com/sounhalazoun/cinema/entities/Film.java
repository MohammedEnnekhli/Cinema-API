package com.sounhalazoun.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String titre;
    private LocalTime duree;
    @Column(length = 75)
    private String  realisateur;
    private String description;
    private String photo;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private  Date dateSortie;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
   @ManyToMany
   @JoinTable(name = "Film_Categorie",joinColumns = @JoinColumn(name = "filmId"),
           inverseJoinColumns = @JoinColumn(name = "categorieId"))
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Categorie> categories;
}
