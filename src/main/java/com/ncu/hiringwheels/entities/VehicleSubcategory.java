package com.ncu.hiringwheels.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class VehicleSubcategory {
    @Id
    private int vehicleSubcategoryId;

    @Column(length = 50, nullable = false, unique = true)
    private String vehicleSubcategoryName;

    @Column(nullable = false)
    private int pricePerDay;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id", nullable = false)
    private VehicleCategory vehicleCategory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vehicleSubcategory")
    private Set<Vehicle> vehicles;

    public VehicleSubcategory() {
    }

    public VehicleSubcategory(int vehicleSubcategoryId, String vehicleSubcategoryName, int pricePerDay, VehicleCategory vehicleCategory) {
        this.vehicleSubcategoryId = vehicleSubcategoryId;
        this.vehicleSubcategoryName = vehicleSubcategoryName;
        this.pricePerDay = pricePerDay;
        this.vehicleCategory = vehicleCategory;
    }

    public int getVehicleSubcategoryId() {
        return vehicleSubcategoryId;
    }

    public void setVehicleSubcategoryId(int vehicleSubcategoryId) {
        this.vehicleSubcategoryId = vehicleSubcategoryId;
    }

    public String getVehicleSubcategoryName() {
        return vehicleSubcategoryName;
    }

    public void setVehicleSubcategoryName(String vehicleSubcategoryName) {
        this.vehicleSubcategoryName = vehicleSubcategoryName;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleSubcategory{" +
                "vehicleSubcategoryId=" + vehicleSubcategoryId +
                ", vehicleSubcategoryName='" + vehicleSubcategoryName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", vehicleCategory=" + vehicleCategory +
                '}';
    }
}
