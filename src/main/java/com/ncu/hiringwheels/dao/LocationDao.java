package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<Location, Integer> {
}
