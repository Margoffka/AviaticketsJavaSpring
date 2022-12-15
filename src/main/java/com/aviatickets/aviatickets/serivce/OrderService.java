package com.aviatickets.aviatickets.serivce;

import com.aviatickets.aviatickets.models.Order;
import com.aviatickets.aviatickets.repo.FlightRepository;
import com.aviatickets.aviatickets.repo.LuggageRepository;
import com.aviatickets.aviatickets.repo.OrderRepository;
import com.aviatickets.aviatickets.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final FlightRepository flightRepository;

    private final LuggageRepository luggageRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, FlightRepository flightRepository, LuggageRepository luggageRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.luggageRepository = luggageRepository;
    }

    @Transactional
    public void createOrder(Integer userId, Integer flightId, String name, String surname, String passportIndex, Integer luggageId) {
        Order order = new Order(name, surname, passportIndex);
        order.setUser(userRepository.findById(userId).get());
        order.setFlight(flightRepository.findById(flightId).get());
        order.setLuggage(luggageRepository.findById(luggageId).get());
        orderRepository.save(order);
    }

    public List<Order> getAllForCurrentUser(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
