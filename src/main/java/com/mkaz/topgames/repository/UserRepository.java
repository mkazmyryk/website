package com.mkaz.topgames.repository;

import com.mkaz.topgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserName(String username);

    User findByEmailIgnoreCase(String email);

    User findByUserNameIgnoreCase(String userName);
}