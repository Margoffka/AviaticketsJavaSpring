package com.aviatickets.aviatickets.repo;

import com.aviatickets.aviatickets.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
