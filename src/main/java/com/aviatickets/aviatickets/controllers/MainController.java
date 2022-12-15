package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.models.Flight;
import com.aviatickets.aviatickets.models.Order;
import com.aviatickets.aviatickets.repo.CompanyRepository;
import com.aviatickets.aviatickets.repo.FlightRepository;
import com.aviatickets.aviatickets.security.CustomUserDetails;
import com.aviatickets.aviatickets.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Controller
public class MainController {

    private final CompanyRepository companyRepository;

    private final FlightRepository flightRepository;

    private final OrderService orderService;

    @Autowired
    public MainController(CompanyRepository companyRepository, FlightRepository flightRepository, OrderService orderService) {
        this.companyRepository = companyRepository;
        this.flightRepository = flightRepository;
        this.orderService = orderService;
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "admin";
    }

    @GetMapping("/account")
    public String account(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Order> orders = orderService.getAllForCurrentUser(userDetails.getUser().getId());
        model.addAttribute("orders", orders);
        return "account";
    }


    @PostMapping("/admin")
    public String flightAdd(@RequestParam String flight_date, @RequestParam String departure_time,
                            @RequestParam String departure_from, @RequestParam String arrival_time,
                            @RequestParam String arrival_to, @RequestParam Float flight_cost,
                            @RequestParam("aviaCompanyId") Integer aviaCompanyId, Model model) {

        Flight flight = new Flight(Date.valueOf(flight_date), Time.valueOf(departure_time), Time.valueOf(arrival_time), departure_from, arrival_to, flight_cost);
        flight.setCompany(companyRepository.findById(aviaCompanyId).get());
        flightRepository.save(flight);
        return "redirect:/admin";
    }

    /*@PostMapping("/")
    public String chooseFlightRequest(@RequestParam String input_date, Model model) {
        List<Flights> flights = flightsRepository.findByFlight_date(Date.valueOf(input_date));
        model.addAttribute("flights", flights);
        return "redirect:/choose";
    }*/
}
