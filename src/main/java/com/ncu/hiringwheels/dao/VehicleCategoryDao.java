package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCategoryDao extends JpaRepository<VehicleCategory, Integer> {
    VehicleCategory findByVehicleCategoryName(String category);
    VehicleCategory findByVehicleCategoryId(int categoryId);
}
