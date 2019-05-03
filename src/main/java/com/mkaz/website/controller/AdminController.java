package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.entity.Genre;
import com.mkaz.website.entity.Platform;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class AdminController {
    @Autowired
    private GamesRepository gamesRepository;

    @GetMapping("/admin/add")
    public String addGameForm(Model model) {
        model.addAttribute("platforms", Platform.values());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("game", new Game());
        return "add";
    }

    @PostMapping("/admin/add")
    public String addGame(Game game, @RequestParam("logo") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("./static/images/"
                            + file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        game.setImageLink("/images/" + file.getOriginalFilename());
        game.setAvrRating(0.0);
        gamesRepository.save(game);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteGame(Model model, String title) {
        model.addAttribute("title", title);
        return "delete";
    }

}
