package com.ncu.hiringwheels.validator;

import com.ncu.hiringwheels.exceptions.APIException;

import java.text.ParseException;
import java.util.Date;

public interface VehicleValidator {
    void validateGetAllVehicles(String categoryName, Date pickupDate, Date dropDate, int locationId) throws APIException, ParseException;
    void validateUser(int userId) throws APIException;
}
