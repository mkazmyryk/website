package com.mkaz.website.repository;

import com.mkaz.website.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
