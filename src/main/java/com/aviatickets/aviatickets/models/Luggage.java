package com.aviatickets.aviatickets.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "luggage")
public class Luggage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "luggage_type")
    private String luggageType;

    @OneToMany(mappedBy = "luggage")
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }
}
