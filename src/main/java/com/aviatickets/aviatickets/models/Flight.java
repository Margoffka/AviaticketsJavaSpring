package com.aviatickets.aviatickets.models;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.util.List;


@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "flight_date")
    private Date flightDate;
    @Column(name = "departure_time")
    private Time departureTime;
    @Column(name = "arrival_time")
    private Time arrivalTime;
    @Column(name = "departure_from")
    private String departureFrom;
    @Column(name = "arrival_to")
    private String arrivalTo;
    @Column(name = "flight_cost")
    private Float flightCost;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "flight", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Flight() {
    }

    public Flight(Date flightDate, Time departureTime, Time arrivalTime, String departureFrom, String arrivalTo, Float flightCost) {
        this.id = id;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureFrom = departureFrom;
        this.arrivalTo = arrivalTo;
        this.flightCost = flightCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public Float getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(Float flightCost) {
        this.flightCost = flightCost;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

