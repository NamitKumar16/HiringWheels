package com.ncu.hiringwheels.controllers;

import com.ncu.hiringwheels.dto.BookingDTO;
import com.ncu.hiringwheels.entities.Booking;
import com.ncu.hiringwheels.exceptions.APIException;
import com.ncu.hiringwheels.exceptions.InsufficientBalanceException;
import com.ncu.hiringwheels.services.BookingService;
import com.ncu.hiringwheels.utils.DTOEntityConverter;
import com.ncu.hiringwheels.utils.EntityDTOConverter;
import com.ncu.hiringwheels.validator.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value="/hirewheels/v1")
public class BookingController {

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingValidator bookingValidator;

    /**
     *
     * @param bookingDTO
     * @throws APIException
     * @throws ParseException
     * @throws InsufficientBalanceException
     */
    @PostMapping("/bookings")
    public ResponseEntity addBooking(@RequestBody BookingDTO bookingDTO) throws APIException, ParseException, InsufficientBalanceException {
        ResponseEntity responseEntity = null;
        bookingValidator.validateBooking(bookingDTO);
        Booking booking = dtoEntityConverter.convertToBookingEntity(bookingDTO);
        Booking responseBooking = bookingService.addBooking(booking);
        responseEntity = ResponseEntity.ok(entityDTOConverter.convertToBookingDTO(responseBooking));
        return responseEntity;
    }

}
