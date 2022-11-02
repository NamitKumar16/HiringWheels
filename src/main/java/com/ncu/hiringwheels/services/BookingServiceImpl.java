package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.dao.BookingDao;
import com.ncu.hiringwheels.dao.UserDao;
import com.ncu.hiringwheels.entities.Booking;
import com.ncu.hiringwheels.entities.User;

import com.ncu.hiringwheels.exceptions.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingDao bookingDao;

    @Autowired
    UserDao userDao;

    /**
     * This method adds booking for a particular vehicle in the database. While adding the booking,
     * the booking amount should be deducted from the wallet balance of the user.
     * @param booking
     * @return
     * @throws InsufficientBalanceException
     */

    public Booking addBooking(Booking booking) throws InsufficientBalanceException {

        User user = booking.getUser();
        if (user.getWalletMoney() < booking.getAmount()) {
            throw new InsufficientBalanceException("Insufficient Balance. Please Check With Admin.");
        } else {
            user.setWalletMoney(user.getWalletMoney() - booking.getAmount());
            userDao.save(user);
        }
        Booking savedBooking = bookingDao.save(booking);
        return savedBooking;
    }
}
