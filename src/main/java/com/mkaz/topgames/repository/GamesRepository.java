package com.mkaz.topgames.repository;

import com.mkaz.topgames.entity.Game;
import com.mkaz.topgames.entity.Genre;
import com.mkaz.topgames.entity.Platform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<Game, Long> {

    Page<Game> findAll(Pageable pageable);

    Game findByTitle(String title);

    Page<Game> findAllByGenre(Genre genre, Pageable pageable);

    Page<Game> findAllByTitle(String title, Pageable pageable);

    Page<Game> findAllByPlatform(Platform platform, Pageable pageable);

    Page<Game> findAllByGenreAndPlatform(Genre genre, Platform platform, Pageable pageable);

    Page<Game> findAllByReleaseDateAfter(String date, Pageable pageable);

    Page<Game> findAllByTitleContainingIgnoreCase(String containing, Pageable pageable);

    Integer deleteByTitle(String title);

}
