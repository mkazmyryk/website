package com.mkaz.topgames.repository;

import com.mkaz.topgames.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
