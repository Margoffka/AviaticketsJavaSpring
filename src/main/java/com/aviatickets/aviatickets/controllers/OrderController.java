package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.security.CustomUserDetails;
import com.aviatickets.aviatickets.serivce.CustomUserDetailsService;
import com.aviatickets.aviatickets.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/add/{flightId}")
    public String newOrder(@PathVariable("flightId") Integer flightId, String name, String surname, String passportIndex,
                           @RequestParam("luggageId") Integer luggageId,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {


        Integer userId = userDetails.getUser().getId();
        orderService.createOrder(userId, flightId, name, surname, passportIndex, luggageId);
        return "redirect:/account";
    }
}
