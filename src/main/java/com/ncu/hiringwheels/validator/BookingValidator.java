package com.ncu.hiringwheels.validator;

import com.ncu.hiringwheels.dto.BookingDTO;
import com.ncu.hiringwheels.exceptions.APIException;

import java.text.ParseException;

public interface BookingValidator {
    void validateBooking(BookingDTO vehicle) throws ParseException, APIException;
}
