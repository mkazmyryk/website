package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private GamesRepository gamesRepository;

    @Autowired
    public MainController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }


    @GetMapping("/feed")
    public String gamesForFeed(Model model) {
        List<Game> gameList = gamesRepository.findAll();
        model.addAttribute("games", gameList);
        return "feed";
    }


//    @GetMapping(value = "/")
//    public ModelAndView main(ModelAndView modelAndView){
//        System.out.println("DONE");
//        modelAndView.setViewName("hello");
//        Game game = new Game();
//        game.setTitle("PUBG");
//        game.setGenre("ACTION");
//        gamesRepository.save(game);
//        return modelAndView;
//    }
}
