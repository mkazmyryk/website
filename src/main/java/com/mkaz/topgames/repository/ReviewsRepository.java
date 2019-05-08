package com.mkaz.topgames.repository;

import com.mkaz.topgames.entity.Review;
import com.mkaz.topgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {

    Review findByUser(User user);
}
