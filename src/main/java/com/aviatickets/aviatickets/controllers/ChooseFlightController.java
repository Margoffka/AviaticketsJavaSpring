package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.models.Flight;
import com.aviatickets.aviatickets.repo.FlightRepository;
import com.aviatickets.aviatickets.serivce.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ChooseFlightController {

    private final FlightRepository flightRepository;

    private final FlightService flightService;

    @Autowired
    public ChooseFlightController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
    }

    @GetMapping("/choose")
    public String chooseFlight(Model model) {
//        Iterable<Flight> flights = flightRepository.findAll();
//        model.addAttribute("flights", flights);
        return "choose-flight";
    }

    @GetMapping("/tickets/{id}")
    public String tickets(@PathVariable(value = "id") Integer id, Model model) {
        if(!flightRepository.existsById(id)) {
            return "redirect:/choose";
        }

        Optional<Flight> flights = flightRepository.findById(id);
        ArrayList<Flight> res = new ArrayList<>();
        flights.ifPresent(res::add);
        model.addAttribute("flight", res);
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
