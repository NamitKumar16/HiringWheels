package com.ncu.hiringwheels.dao;

import com.ncu.hiringwheels.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
