package com.sounhalazoun.cinema;

import com.sounhalazoun.cinema.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestCinemaApplication implements CommandLineRunner {
     @Autowired
     private ICinemaInitService cinemaInitService;
    public static void main(String[] args) {
        SpringApplication.run(RestCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*cinemaInitService.initVilles();
cinemaInitService.initCinemas();
cinemaInitService.initSalles();
cinemaInitService.initPlaces();
cinemaInitService.initSeances();
cinemaInitService.initCategories();
cinemaInitService.initFilms();
cinemaInitService.initPrpjections();
cinemaInitService.initTickets();*/
    }
}
