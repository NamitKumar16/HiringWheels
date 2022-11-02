package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.entities.Vehicle;
import com.ncu.hiringwheels.exceptions.VehicleNotFoundException;
import com.ncu.hiringwheels.exceptions.VehicleNumberNotUniqueException;

public interface AdminService {
    Vehicle registerVehicle(Vehicle vehicle) throws VehicleNumberNotUniqueException;
    Vehicle changeAvailability(int vehicleId, int availabilityStatus)throws VehicleNotFoundException;
}
