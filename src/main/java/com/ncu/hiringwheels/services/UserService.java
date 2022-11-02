package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.dto.LoginDTO;
import com.ncu.hiringwheels.entities.User;
import com.ncu.hiringwheels.exceptions.APIException;
import com.ncu.hiringwheels.exceptions.BadCredentialsException;
import com.ncu.hiringwheels.exceptions.UserAlreadyExistsException;
import com.ncu.hiringwheels.exceptions.UserNotFoundException;

public interface UserService {
    User getUser(User user) throws APIException, UserNotFoundException, BadCredentialsException;
    User getUserDetails(LoginDTO loginDTO) throws APIException, UserNotFoundException, BadCredentialsException;
    User createUser(User user) throws APIException, UserAlreadyExistsException;
    User getUserDetailsByEmail(String email) throws UserNotFoundException;
}
