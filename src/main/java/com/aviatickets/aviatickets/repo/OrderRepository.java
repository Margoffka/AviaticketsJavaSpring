package com.aviatickets.aviatickets.repo;

import com.aviatickets.aviatickets.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer>  {
    List<Order> findAllByUserId(Integer userId);
}
