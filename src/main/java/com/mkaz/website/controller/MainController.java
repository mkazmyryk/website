package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private GamesRepository gamesRepository;

    @Autowired
    public MainController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }


    @GetMapping("/feed")
    public String gamesForFeed(Model model) {
        Page<Game> page = gamesRepository.findAll(PageRequest.of(0, 2));
        model.addAttribute("games", page);
        return "feed";
    }

}
