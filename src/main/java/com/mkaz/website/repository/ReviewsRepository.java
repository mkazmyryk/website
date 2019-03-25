package com.mkaz.website.repository;

import com.mkaz.website.entity.Review;
import com.mkaz.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    Review findByUser(User user);
}
