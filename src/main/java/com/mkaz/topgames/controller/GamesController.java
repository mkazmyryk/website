package com.mkaz.topgames.controller;

import com.mkaz.topgames.entity.Game;
import com.mkaz.topgames.entity.Review;
import com.mkaz.topgames.repository.GamesRepository;
import com.mkaz.topgames.repository.ReviewsRepository;
import com.mkaz.topgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Controller
public class GamesController {
    private final GamesRepository gamesRepository;
    private final ReviewsRepository reviewsRepository;
    private final UserRepository userRepository;

    @Autowired
    public GamesController(GamesRepository gamesRepository, ReviewsRepository reviewsRepository,
                           UserRepository userRepository) {
        this.gamesRepository = gamesRepository;
        this.reviewsRepository = reviewsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/game/{gameTitle}")
    public String gameInfo(@PathVariable String gameTitle, Model model) {
        Game game = gamesRepository.findByTitle(gameTitle);
        if (game != null) {
            model.addAttribute("game", game);
            model.addAttribute("reviews", game.getReviews());
        } else {
            throw new NoSuchElementException();
        }
        return "game";
    }

    @GetMapping("/review")
    public String viewForm(Model model, String title) {
        model.addAttribute("review", new Review());
        model.addAttribute("title", title);
        return "review";
    }

    @PostMapping("/review")
    public String addReview(Review review, HttpServletRequest request, String title) {
        review.setDate(LocalDateTime.now());
        review.setUser(userRepository.findUserByUserName(request.getRemoteUser()));
        Game game = gamesRepository.findByTitle(title);
        game.addReview(review);
        reviewsRepository.save(review);
        gamesRepository.save(game);
        return "redirect:/feed";
    }
}
