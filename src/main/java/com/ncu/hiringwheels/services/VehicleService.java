package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.entities.Vehicle;
import com.ncu.hiringwheels.exceptions.APIException;

import java.util.Date;
import java.util.List;

public interface VehicleService {
    public List<Vehicle> getAvailableVehicles(String categoryName, Date pickUpDate, Date dropDate, int locationId) throws APIException;
    public List<Vehicle> getAllVehicles() throws APIException;
}
