package com.mkaz.website.controller;

import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private GamesRepository gamesRepository;

    @Autowired
    public MainController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
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
