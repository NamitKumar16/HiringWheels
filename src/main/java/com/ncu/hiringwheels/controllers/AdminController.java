package com.ncu.hiringwheels.controllers;

import com.ncu.hiringwheels.dto.VehicleDTO;
import com.ncu.hiringwheels.entities.Vehicle;
import com.ncu.hiringwheels.exceptions.*;
import com.ncu.hiringwheels.responsemodel.CustomResponse;
import com.ncu.hiringwheels.services.AdminService;
import com.ncu.hiringwheels.services.UserService;
import com.ncu.hiringwheels.validator.AdminRequestValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value="/hirewheels/v1")
public class AdminController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    AdminRequestValidator adminRequestValidator;

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    /**
     *
     * @param vehicleDTO
     * @throws APIException
     * @throws VehicleNumberNotUniqueException
     */

    @PostMapping("/vehicles")
    public ResponseEntity addVehicleRequest(@RequestBody VehicleDTO vehicleDTO, @RequestHeader(value = "ACCESS-TOKEN") String accessToken) throws APIException, VehicleNumberNotUniqueException, BadCredentialsException {

        logger.info("Inside addVehicleRequest() method");
        if(accessToken == null)
            throw new APIException("Please add proper authentication");
        if(!userService.getUserDetailsByEmail(accessToken).getRole().getRoleName().equalsIgnoreCase("Admin"))
            throw new BadCredentialsException("Unauthorized. Only 'Admin' can access this API");
        logger.debug("Access token validated");
        adminRequestValidator.validateAddVehicleRequest(vehicleDTO);
        logger.debug("Vehicle details validated");
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        adminService.registerVehicle(vehicle);
        logger.debug("Accepted new vehicle details: "+ vehicle);
        CustomResponse response = new CustomResponse(new Date(), "Vehicle Added Successfully", 200);
        logger.debug("Returning successful vehicle addition response: "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param vehicleDTO
     * @param vehicleId
     * @throws APIException
     * @throws VehicleNotFoundException
     */

    @PutMapping("vehicles/{vehicleId}")
    public ResponseEntity changeVehicleAvailability(@RequestBody VehicleDTO vehicleDTO, @PathVariable int vehicleId, @RequestHeader(value = "ACCESS-TOKEN") String accessToken) throws APIException, VehicleNotFoundException, BadCredentialsException {

        logger.debug("Change Availability status: vehicle id: "+ vehicleId, vehicleDTO);
        if(accessToken == null)
            throw new APIException("Please add proper authentication");
        if(!userService.getUserDetailsByEmail(accessToken).getRole().getRoleName().equalsIgnoreCase("Admin"))
            throw new BadCredentialsException("Unauthorized. Only 'Admin' can access this API");
        logger.debug("Access token validated");
        int availability_status = vehicleDTO.getAvailabilityStatus();
        adminRequestValidator.validateChangeVehicleAvailability(availability_status);
        logger.debug("Validated Availability Status: "+ availability_status);
        Vehicle vehicle = adminService.changeAvailability(vehicleId, availability_status);
        logger.debug("Changed Availability Status for Vehicle: "+ vehicle);
        CustomResponse response = new CustomResponse(new Date(), "Activity performed successfully", 200);
        logger.debug("Returning successful changing of availability status response: "+response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
