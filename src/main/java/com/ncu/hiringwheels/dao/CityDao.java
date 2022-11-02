package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {
}
