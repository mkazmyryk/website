package com.mkaz.website.repository;

import com.mkaz.website.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<Game, Long> {

    Page<Game> findAll(Pageable pageable);

    Game findByTitle(String title);

    Page<Game> findAllByGenre(String genre, Pageable pageable);

    Page<Game> findAllByPlatform(String platform, Pageable pageable);

    Page<Game> findAllByPlatformAndGenre(String platform, String genre, Pageable pageable);

}
