package com.aviatickets.aviatickets.serivce;

import com.aviatickets.aviatickets.models.Flight;
import com.aviatickets.aviatickets.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findByParams(String departureFrom, String arrivalTo, Date date) {
        return flightRepository.findByDepartureFromStartingWithAndArrivalToStartingWithAndFlightDate(departureFrom, arrivalTo, date);
//        return flightRepository.findByDepartureFrom(departureFrom);
    }
}
