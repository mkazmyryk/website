package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

@Controller
public class GamesController {
    @Autowired
    private GamesRepository gamesRepository;

    @GetMapping(value = "/game/{gameTitle}")
    public String gameInfo(@PathVariable String gameTitle, Model model) {
        Game game = gamesRepository.findByTitle(gameTitle);
        if (game != null) {
            model.addAttribute("game", game);
        } else {
            throw new NoSuchElementException();
        }
        return "game";
    }
}
