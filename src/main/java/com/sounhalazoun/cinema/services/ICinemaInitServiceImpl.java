package com.sounhalazoun.cinema.services;

import com.sounhalazoun.cinema.dao.*;
import com.sounhalazoun.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class ICinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ProjectionRepository projectionRepository;

    @Override
    public void initFilms() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dateList = new ArrayList<>();
        try {
            dateList.add(dateFormat.parse("1995-03-01"));
            dateList.add(dateFormat.parse("1972-10-18"));
            dateList.add(dateFormat.parse("2008-08-13"));
            dateList.add(dateFormat.parse("1994-10-26"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<List<Categorie>> categorieList = new ArrayList<>();
        categorieList.add(Arrays.asList(categorieRepository.getOne(8l)));
        categorieList.add(Arrays.asList(categorieRepository.getOne(6l), categorieRepository.getOne(8l)));
        categorieList.add(Arrays.asList(categorieRepository.getOne(1l), categorieRepository.getOne(6l), categorieRepository.getOne(8l)));
        categorieList.add(Arrays.asList(categorieRepository.getOne(6l), categorieRepository.getOne(8l)));

        int[] durees = new int[]{142, 175, 192, 194};
        String[] description = new String[]{"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."};
        String[] realisateur = new String[]{"Frank Darabont", "Francis Ford Coppola", "Christopher Nolan", "Quentin Tarantino"};
        String[] titre = new String[]{"The Shawshank Redemption ", "The Godfather", "The Dark Knight", "Pulp Fiction"};

        for (int i = 0; i < 4; i++) {
            Film film = new Film();
            film.setTitre(titre[i]);
            film.setDuree(LocalTime.MIN.plus(
                    Duration.ofMinutes(Long.valueOf(durees[i]))
            ));
            film.setDescription(description[i]);
            film.setRealisateur(realisateur[i]);
            film.setDateSortie(dateList.get(i));
            film.setCategories(categorieList.get(i));
            film.setPhoto(film.getTitre().replaceAll(" ","")+".jpg");
            filmRepository.save(film);
        }
    }


    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setNom("Salle" + (i + 1));
                salle.setCinema(cinema);
                salle.setNombrePlaces(25 + (int) (50 * Math.random()));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Action", "Adventure", "Animation", "Biography", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "Film Noir", "History", "Horror", "Music", "Musical",
                "Mystery", "Romance", "Sci-Fi", "Short", "Sport", "Superhero", "Thriller", "War", "Western").forEach(nomCat->{
                    Categorie categorie=new Categorie();
                    categorie.setNom(nomCat);
                    categorieRepository.save(categorie);
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlaces(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(heure -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(heure));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initTickets() {
      projectionRepository.findAll().forEach(projection -> {
          projection.getSalle().getPlaces().forEach(place -> {
              Ticket ticket=new Ticket();
              ticket.setPlace(place);
              ticket.setProjection(projection);
              ticket.setReservee(false);
              ticket.setPrix(projection.getPrix());
              ticketRepository.save(ticket);
          });
      });
    }

    @Override
    public void initVilles() {
        Stream.of("Rabat", "Casablanca", "Tanger", "Marrakech").forEach(v -> {
            Ville ville = new Ville();
            ville.setNom(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v -> {
            Stream.of("Megarama", "IMAX", "Founoun", "Chahrazad", "Dawliz")
                    .forEach(nameCinema -> {
                        Cinema cinema = new Cinema();
                        cinema.setNom(nameCinema);
                        cinema.setNombreSalles(3 + (int) (Math.random() * 7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);
                    });

        });

    }

    @Override
    public void initPrpjections() {
        int[] prix=new int[]{30,50,70,100};
      villeRepository.findAll().forEach(ville -> {
          ville.getCinemas().forEach(cinema -> {
              cinema.getSalles().forEach(salle -> {
                  filmRepository.findAll().forEach(film -> {
                      seanceRepository.findAll().forEach(seance -> {
                          Projection projection=new Projection();
                          projection.setFilm(film);
                          projection.setSalle(salle);
                          projection.setPrix(prix[new Random().nextInt(prix.length)]);
                          projection.setDateProjection(new Date());
                          projection.setSeance(seance);
                          projectionRepository.save(projection);
                      });


                  });
              });
          });
      });
    }
}
