package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private GamesRepository gamesRepository;

    @GetMapping("/add")
    public String addGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "add";
    }

    @PostMapping("/add")
    public String addGame(Game game) {
        gamesRepository.save(game);
        return "redirect:/admin";
    }

}
