package com.ncu.hiringwheels.eh;

import com.ncu.hiringwheels.exceptions.*;
import com.ncu.hiringwheels.responsemodel.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({UserNotFoundException.class, VehicleNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(Exception ex) {
        return error(NOT_FOUND, ex);
    }

    @ExceptionHandler({APIException.class, UserAlreadyExistsException.class, VehicleNumberNotUniqueException.class})
    public ResponseEntity<Object> handleAPIException(Exception ex) {
        return error(BAD_REQUEST, ex);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return error(FORBIDDEN, ex);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return error(UNAUTHORIZED, ex);
    }

    @ExceptionHandler({SQLException.class, NullPointerException.class})
    public ResponseEntity<Object> handle(Exception ex) {
        logger.error("Exception : ", ex);
        return error(BAD_REQUEST, ex);
    }

    private ResponseEntity error(HttpStatus status, Exception ex) {
        logger.error("Exception : ", ex);
        CustomResponse customResponse = new CustomResponse(new Date(), ex.getMessage(), status.value());
        return new ResponseEntity(customResponse, status);
    }

}
