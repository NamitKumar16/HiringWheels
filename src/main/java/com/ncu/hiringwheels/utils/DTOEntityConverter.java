package com.ncu.hiringwheels.utils;

import com.ncu.hiringwheels.dao.LocationDao;
import com.ncu.hiringwheels.dao.VehicleDao;
import com.ncu.hiringwheels.dao.UserDao;
import com.ncu.hiringwheels.dto.BookingDTO;
import com.ncu.hiringwheels.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOEntityConverter {

    @Autowired
    LocationDao locationDao;

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserDao userDao;

    public Booking convertToBookingEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setAmount(bookingDTO.getAmount());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setDropoffDate(bookingDTO.getDropoffDate());
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setLocation(locationDao.findById(bookingDTO.getLocationId()).get());
        booking.setVehicleWithBooking(vehicleDao.findById(bookingDTO.getVehicleId()).get());
        booking.setUser(userDao.findById(bookingDTO.getUserId()).get());
        return booking;
    }

}
