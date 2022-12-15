package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.models.Flight;
import com.aviatickets.aviatickets.models.User;
import com.aviatickets.aviatickets.repo.FlightRepository;
import com.aviatickets.aviatickets.repo.LuggageRepository;
import com.aviatickets.aviatickets.security.CustomUserDetails;
import com.aviatickets.aviatickets.serivce.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class ChooseFlightController {

    private final FlightRepository flightRepository;

    private final FlightService flightService;

    private final LuggageRepository luggageRepository;

    @Autowired
    public ChooseFlightController(FlightRepository flightRepository, FlightService flightService, LuggageRepository luggageRepository) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
        this.luggageRepository = luggageRepository;
    }

    @GetMapping("/choose")
    public String chooseFlight(Model model) {
//        Iterable<Flight> flights = flightRepository.findAll();
//        model.addAttribute("flights", flights);
        return "choose-flight";
    }

    @GetMapping("/tickets/{id}")
    public String tickets(@PathVariable(value = "id") Integer id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(!flightRepository.existsById(id)) {
            return "redirect:/choose";
        }


        model.addAttribute("flight", flightRepository.findById(id).get());

        User currentUser = userDetails.getUser();

        model.addAttribute("luggageTypes", luggageRepository.findAll());

        model.addAttribute("name", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());

        return "tickets";
    }

    @GetMapping("chooseByAttributes")
    public String chooseByAttributes(@RequestParam("input_departure_from") String departureFrom,
                                     @RequestParam("input_arrival_to") String arrivalTo,
                                     @RequestParam("input_date") Date date, Model model) {
        List<Flight> flights =  flightService.findByParams(departureFrom, arrivalTo, date);

        model.addAttribute("flights", flights);


        return "choose-flight";
    }
}
