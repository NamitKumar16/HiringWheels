package com.ncu.hiringwheels.validator;

import com.ncu.hiringwheels.dto.LoginDTO;
import com.ncu.hiringwheels.dto.UserDTO;
import com.ncu.hiringwheels.exceptions.APIException;

public interface UserValidator {
    void validateuserLogin(LoginDTO user) throws APIException;
    void validateUserSignUp(UserDTO user) throws APIException;
}