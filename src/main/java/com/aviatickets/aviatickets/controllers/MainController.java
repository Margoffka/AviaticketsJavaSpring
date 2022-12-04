package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.models.Flight;
import com.aviatickets.aviatickets.repo.FlightRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;


@Controller
public class MainController {

    public MainController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    private final FlightRepository flightRepository;

    @PostMapping("/admin")
    public String flightAdd(@RequestParam String flight_date, @RequestParam String departure_time,
                            @RequestParam String departure_from, @RequestParam String arrival_time,
                            @RequestParam String arrival_to, @RequestParam Float flight_cost, Model model) {

        Flight flight = new Flight(Date.valueOf(flight_date), Time.valueOf(departure_time), Time.valueOf(arrival_time), departure_from, arrival_to, flight_cost);
        flightRepository.save(flight);
        return "admin";
    }

    /*@PostMapping("/")
    public String chooseFlightRequest(@RequestParam String input_date, Model model) {
        List<Flights> flights = flightsRepository.findByFlight_date(Date.valueOf(input_date));
        model.addAttribute("flights", flights);
        return "redirect:/choose";
    }*/
}
