package com.mkaz.website.repository;

import com.mkaz.website.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
}
