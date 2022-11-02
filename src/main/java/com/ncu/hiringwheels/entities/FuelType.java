package com.ncu.hiringwheels.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FuelType {
    @Id
    private int fuelTypeId;

    @Column(length = 50, nullable = false, unique = true)
    private String fuelType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fuelType")
    private Set<Vehicle> vehicles;

    public FuelType() {
    }

    public FuelType(int fuelTypeId, String fuelType) {
        this.fuelTypeId = fuelTypeId;
        this.fuelType = fuelType;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "fuelTypeId=" + fuelTypeId +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
