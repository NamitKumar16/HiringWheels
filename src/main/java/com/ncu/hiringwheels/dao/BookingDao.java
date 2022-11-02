package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.Booking;
import com.ncu.hiringwheels.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Integer> {
    List<Booking> findByVehicleWithBooking(Vehicle vehicle);
}
