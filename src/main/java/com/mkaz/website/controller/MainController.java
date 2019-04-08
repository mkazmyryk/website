package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    private GamesRepository gamesRepository;
    private int totalPages;
    private List<Integer> pageNumbers;
    private Page<Game> games;
    private int currentPage;
    private int pageSize;

    @Autowired
    public MainController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }


    @GetMapping("/feed")
    public String gamesForFeed(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        currentPage = page.orElse(1);
        pageSize = size.orElse(5);

        games = gamesRepository.findAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("games", games);

        totalPages = ((int) (gamesRepository.count() / pageSize)) + 1;


        if (totalPages > 1) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "feed";
    }

    @GetMapping("/best")
    public String bestGames(Model model, @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {

        currentPage = page.orElse(1);
        pageSize = size.orElse(10);
        totalPages = ((int) (gamesRepository.count() / pageSize)) + 1;

        games = gamesRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by("avrRating")
                .descending()));

        model.addAttribute("games", games);

        if (totalPages > 1) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "best";
    }

}
