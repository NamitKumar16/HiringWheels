package com.ncu.hiringwheels.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class VehicleCategory {
    @Id
    private int vehicleCategoryId;

    @Column(length = 50, nullable = false, unique = true)
    private String vehicleCategoryName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehicleCategory")
    private Set<VehicleSubcategory> vehicleSubcategories;

    public VehicleCategory() {
    }

    public VehicleCategory(int vehicleCategoryId, String vehicleCategoryName) {
        this.vehicleCategoryId = vehicleCategoryId;
        this.vehicleCategoryName = vehicleCategoryName;
    }

    public int getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(int vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public String getVehicleCategoryName() {
        return vehicleCategoryName;
    }

    public void setVehicleCategoryName(String vehicleCategoryName) {
        this.vehicleCategoryName = vehicleCategoryName;
    }

    public Set<VehicleSubcategory> getVehicleSubcategories() {
        return vehicleSubcategories;
    }

    public void setVehicleSubcategories(Set<VehicleSubcategory> vehicleSubcategories) {
        this.vehicleSubcategories = vehicleSubcategories;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "vehicleCategoryId=" + vehicleCategoryId +
                ", vehicleCategoryName='" + vehicleCategoryName + '\'' +
                '}';
    }
}
