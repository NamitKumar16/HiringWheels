package com.ncu.hiringwheels.validator;

import com.ncu.hiringwheels.dto.VehicleDTO;
import com.ncu.hiringwheels.exceptions.APIException;

public interface AdminRequestValidator {
    void validateChangeVehicleAvailability (int availability_status) throws APIException;
    void validateAddVehicleRequest(VehicleDTO vehicleDTO) throws APIException;
}
