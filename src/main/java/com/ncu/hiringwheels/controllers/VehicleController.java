package com.ncu.hiringwheels.controllers;

import com.ncu.hiringwheels.dto.VehicleDTO;
import com.ncu.hiringwheels.entities.Vehicle;
import com.ncu.hiringwheels.exceptions.APIException;
import com.ncu.hiringwheels.services.VehicleService;
import com.ncu.hiringwheels.validator.VehicleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/hirewheels/v1")
public class VehicleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleValidator vehicleValidator;

    /**
     *
     * @param categoryName
     * @param pickupDate
     * @param dropDate
     * @param locationId
     * @throws APIException
     * @throws ParseException
     */

    @GetMapping("/vehicles")
    public ResponseEntity getAvailableVehicles(@RequestParam("categoryName") String categoryName, @RequestParam("pickUpDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date pickupDate,@RequestParam("dropDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dropDate, @RequestParam("locationId") Integer locationId) throws APIException, ParseException {

        ResponseEntity responseEntity = null;
        List<Vehicle> vehiclesList;
        if(categoryName == null || categoryName.isEmpty() || dropDate == null || pickupDate == null ||locationId == null){
            vehiclesList = vehicleService.getAllVehicles();
        }
        else {
            vehicleValidator.validateGetAllVehicles(categoryName,pickupDate, dropDate, locationId);
            vehiclesList = vehicleService.getAvailableVehicles(categoryName,pickupDate,dropDate,locationId);
        }

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for(Vehicle vehicle : vehiclesList){
            vehicleDTOList.add(modelMapper.map(vehicle,VehicleDTO.class));
        }
        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }
}
