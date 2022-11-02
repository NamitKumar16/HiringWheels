package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeDao extends JpaRepository<FuelType, Integer> {
}
