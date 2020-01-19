package com.sounhalazoun.cinema.web;

import com.sounhalazoun.cinema.dao.FilmRepository;
import com.sounhalazoun.cinema.dao.TicketRepository;
import com.sounhalazoun.cinema.entities.Film;
import com.sounhalazoun.cinema.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = "/imagefilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable(name = "id") Long id)throws Exception{
        Film film=filmRepository.getOne(id);
        String photoName=film.getPhoto();
        File file=new File("C:/WorkSpace/uploadImages/cinemaApp/"+photoName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping(value = "/payerTickets")
    public List<Ticket> payer(@RequestBody TicketForm ticketForm){
        List<Ticket> listTickets=new ArrayList<>();
        ticketForm.getTickets().forEach(ticketsId->{
            Ticket ticket=ticketRepository.getOne(ticketsId);
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReservee(true);
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticketRepository.save(ticket);
            listTickets.add(ticket);
        });
        return listTickets;
    }
}
@Data
class TicketForm {
    private String nomClient;
    private int codePayement;
    private List<Long> tickets=new ArrayList<>();
}
