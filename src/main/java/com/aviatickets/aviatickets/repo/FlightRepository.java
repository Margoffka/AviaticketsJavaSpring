package com.aviatickets.aviatickets.repo;

import com.aviatickets.aviatickets.models.Flight;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    /*List<Flights> findByFlight_date (Date flight_date);*/

    List<Flight> findByDepartureFromStartingWithAndArrivalToStartingWithAndFlightDate(String departureFrom, String arrivalTo, Date flightDate);

//    List<Flight> findByDepartureFrom(String departureFrom);
}
