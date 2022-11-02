package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.entities.Booking;
import com.ncu.hiringwheels.exceptions.APIException;
import com.ncu.hiringwheels.exceptions.InsufficientBalanceException;

public interface BookingService {
    Booking addBooking(Booking booking) throws APIException, InsufficientBalanceException;
}
