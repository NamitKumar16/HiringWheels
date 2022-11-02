package com.ncu.hiringwheels.validator;

import com.ncu.hiringwheels.dto.LoginDTO;
import com.ncu.hiringwheels.dto.UserDTO;
import com.ncu.hiringwheels.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator {

    //To-DO:Can include isBlank validation if required.

    @Override
    public void validateuserLogin(LoginDTO user) {
        if(user.getEmail() == null || user.getEmail().isEmpty()  || user.getPassword() == null || user.getPassword().isEmpty() ) {
            throw new APIException("Email/Password cannot be null or empty");
        }
    }

    @Override
    public void validateUserSignUp(UserDTO user){
        if(user.getFirstName().isEmpty() || user.getFirstName() ==null){
            throw new APIException("FirstName cannot be null or empty");
        }
        if (user.getPassword().isEmpty() || user.getPassword() ==null || user.getPassword().length()<5){
            throw new APIException("Password cannot be null or empty or less than 5 characters");
        }
        if (user.getEmail().isEmpty() || user.getEmail() ==null ){
            throw new APIException("Email cannot be null or empty");
        }
        if (user.getMobileNo().isEmpty() || user.getMobileNo() == null || user.getMobileNo().length()<10 || user.getMobileNo().length()>10){
            throw new APIException("Mobile Number cannot be null or empty and must be 10 digits");
        }
    }
}
