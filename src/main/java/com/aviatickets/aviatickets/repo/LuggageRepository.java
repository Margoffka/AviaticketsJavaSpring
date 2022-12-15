package com.aviatickets.aviatickets.repo;

import com.aviatickets.aviatickets.models.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage, Integer> {
}
