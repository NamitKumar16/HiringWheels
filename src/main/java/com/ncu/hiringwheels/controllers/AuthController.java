package com.ncu.hiringwheels.controllers;

import com.ncu.hiringwheels.dto.LoginDTO;
import com.ncu.hiringwheels.dto.UserDTO;
import com.ncu.hiringwheels.entities.User;
import com.ncu.hiringwheels.exceptions.APIException;
import com.ncu.hiringwheels.exceptions.BadCredentialsException;
import com.ncu.hiringwheels.exceptions.UserAlreadyExistsException;
import com.ncu.hiringwheels.exceptions.UserNotFoundException;
import com.ncu.hiringwheels.responsemodel.CustomResponse;
import com.ncu.hiringwheels.services.UserService;
import com.ncu.hiringwheels.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value="/hirewheels/v1")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/users/login")
    public ResponseEntity userLogin(@RequestBody LoginDTO loginDTO) throws APIException, UserNotFoundException, BadCredentialsException {
        ResponseEntity responseEntity = null;
        userValidator.validateuserLogin(loginDTO);
        User userDetail = userService.getUserDetails(loginDTO);
        UserDTO userDTO = modelMapper.map(userDetail,UserDTO.class);
        String token = userDetail.getEmail();
        userDTO.setToken(token);
        responseEntity = ResponseEntity.ok(userDTO);
        return responseEntity;
    }

    @PostMapping("/users")
    public ResponseEntity userSignUp(@RequestBody UserDTO userDTO) throws APIException, UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        userValidator.validateUserSignUp(userDTO);
        userDTO.setRoleId(2);
        userDTO.setWalletMoney(10000.00f);
        User savedUser = userService.createUser(modelMapper.map(userDTO, User.class));
        if (savedUser != null) {
            CustomResponse response = new CustomResponse(new Date(), "User Successfully Signed Up", 200);
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }
        return responseEntity;
    }
}
