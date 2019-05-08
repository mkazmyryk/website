package com.mkaz.topgames.repository;

import com.mkaz.topgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserName(String username);

    User findByEmailIgnoreCase(String email);

    User findByUserNameIgnoreCase(String userName);
}