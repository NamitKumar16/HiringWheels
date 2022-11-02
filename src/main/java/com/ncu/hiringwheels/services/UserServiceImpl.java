package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.dao.UserDao;
import com.ncu.hiringwheels.dto.LoginDTO;
import com.ncu.hiringwheels.entities.User;
import com.ncu.hiringwheels.exceptions.BadCredentialsException;
import com.ncu.hiringwheels.exceptions.UserAlreadyExistsException;
import com.ncu.hiringwheels.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    /**
     * Checks if the userDTO mobile number/email is already exists or not.
     * If not exists, saves the user details else throws an error.
     * @param user
     * @return
     * @throws UserAlreadyExistsException
     */

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        User returnedUser = userDao.findByEmailIgnoreCase(user.getEmail());
        if ( returnedUser != null) {
            throw new UserAlreadyExistsException("Email Already Exists");
        }
        User returnedUser1 = userDao.findByMobileNoIgnoreCase(user.getMobileNo());
        if (returnedUser1 != null) {
            throw new UserAlreadyExistsException("Mobile Number Already Exists");
        }
        User savedUser = userDao.save(user);
        return savedUser;
    }

    /**
     * Checks if the user is registered or not.
     * If registered it returns the user details else throws an error.
     * @param user
     * @return
     * @throws BadCredentialsException
     * @throws UserNotFoundException
     */

    @Override
    public User getUser(User user) throws BadCredentialsException, UserNotFoundException {
        User checkUser = userDao.findByEmailIgnoreCase(user.getEmail());
        if (checkUser == null){
            throw new UserNotFoundException("User not Registered");
        }
        User retrievedUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (retrievedUser == null){
            throw new BadCredentialsException("Invalid Credentials");
        }
        return retrievedUser;
    }

    /**
     * Checks if the user is registered or not.
     * If registered it returns the user details else throws an error.
     * @param loginDTO
     * @return
     * @throws BadCredentialsException
     * @throws UserNotFoundException
     */

    @Override
    public User getUserDetails(LoginDTO loginDTO) throws BadCredentialsException, UserNotFoundException {
        User checkUser = userDao.findByEmailIgnoreCase(loginDTO.getEmail());
        if (checkUser == null){
            throw new UserNotFoundException("User not Registered");
        }
        User retrievedUser = userDao.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (retrievedUser == null){
            throw new BadCredentialsException("Invalid Credentials");
        }
        return retrievedUser;
    }

    @Override
    public User getUserDetailsByEmail(String email) throws UserNotFoundException {
        User retrievedUser = userDao.findByEmailIgnoreCase(email);
        if (retrievedUser == null){
            throw new UserNotFoundException("User not Registered");
        }
        return retrievedUser;
    }
}
