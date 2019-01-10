package com.mkaz.website.repository;

import com.mkaz.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserName(String username);
}