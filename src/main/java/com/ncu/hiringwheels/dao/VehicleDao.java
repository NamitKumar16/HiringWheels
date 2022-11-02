package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<Vehicle, Integer> {
    boolean existsByVehicleNumber(String VehicleNumber);
}
