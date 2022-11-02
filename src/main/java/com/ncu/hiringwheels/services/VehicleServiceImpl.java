package com.ncu.hiringwheels.services;

import com.ncu.hiringwheels.dao.*;
import com.ncu.hiringwheels.entities.Booking;
import com.ncu.hiringwheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    VehicleCategoryDao vehicleCategoryDao;

    @Autowired
    BookingDao bookingDao;


    /**
     * Returns all the vehicle registered on the application.
     * @param
     * @return
     */

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> returnedVehicleList = vehicleDao.findAll();
        return returnedVehicleList;
    }

    /**
     * Returns all the available vehicle in the requested Category for
     * booking with respect to Date, Location and Availability.
     * @param categoryName
     * @param pickUpDate
     * @param dropDate
     * @param locationId
     * @return
     */

    @Override
    public List<Vehicle> getAvailableVehicles(String categoryName, Date pickUpDate,Date dropDate, int locationId) {

        List<Vehicle> returnedVehicleList = new ArrayList<>();

        /**
         * Fetch a list of all vehicles of given vehicle category which are available in the desired location
         * and with availability_status = 1
         */
        vehicleCategoryDao.findByVehicleCategoryName(categoryName).getVehicleSubcategories()
                .forEach(a -> a.getVehicles()
                        .forEach(b -> {
                            if(b.getLocation().getLocationId() == locationId && b.getAvailabilityStatus() == 1)
                                returnedVehicleList.add(b);
                        }));

        /**
         *  Get a list of all the vehicle Ids which have booking during input booking slot.
         *  A vehicle is unavailable for booking if any of the following three scenarios are met-
         *
         *  a. booking pick up date > Booked Vehicle's pickup date &&
         *  booking pick up date < Booked Vehicle's dropoff date
         *
         *  b. booking drop off date > Booked Vehicle's pickup date &&
         *  booking drop off date < Booked Vehicle's dropoff date
         *
         *  c. booking pickup date < Booked vehicle's pick up date &&
         *  booking drop off date > Booked vehicle's drop off date
         *
         * Apart from this, we also need to consider those vehicles as booked if booking pick or dropoff date
         * equals to either booked vehicle's pickup date or dropoff date.
         *
         */
        List<Integer> bookedVehicleIdList = new ArrayList<>();
        returnedVehicleList.forEach(a-> {
            List<Booking> bookedVehicleList = bookingDao.findByVehicleWithBooking(a);
            bookedVehicleList.forEach(b ->{
                if ((pickUpDate.after(b.getPickupDate()) && pickUpDate.before(b.getDropoffDate()))
                        || (dropDate.after(b.getPickupDate()) && dropDate.before(b.getDropoffDate()))
                        || (pickUpDate.before(b.getPickupDate()) && dropDate.after(b.getDropoffDate()))
                        || pickUpDate.equals(b.getDropoffDate())
                        || dropDate.equals(b.getPickupDate())
                        || pickUpDate.equals(b.getPickupDate())
                        || dropDate.equals(b.getDropoffDate())){
                    bookedVehicleIdList.add(b.getVehicleWithBooking().getVehicleId());
                }
            });
        });

        /**
         * Filter out those vehicles from the returnedVehicleList
         * which are already booked in the booking slot.
         */
        List<Vehicle> availableVehicles = new ArrayList<>();
        returnedVehicleList.forEach(a-> {
            if(!bookedVehicleIdList.contains(a.getVehicleId() )){
                availableVehicles.add(a);
            }
        });
        return availableVehicles;
    }

}

