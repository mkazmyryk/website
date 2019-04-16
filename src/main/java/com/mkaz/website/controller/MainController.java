package com.mkaz.website.controller;

import com.mkaz.website.entity.Game;
import com.mkaz.website.entity.Genre;
import com.mkaz.website.entity.Platform;
import com.mkaz.website.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    private GamesRepository gamesRepository;
    private int totalPages;
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

        totalPages = countPages(gamesRepository, pageSize);

        if (totalPages > 1) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }
        return "feed";
    }

    @GetMapping("/best")
    public String bestGames(Model model, @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {

        currentPage = page.orElse(1);
        pageSize = size.orElse(10);

        games = gamesRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by("avrRating")
                .descending()));

        model.addAttribute("platforms", Platform.values());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("games", games);

        totalPages = countPages(gamesRepository, pageSize);

        if (totalPages > 1) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }
        return "best";
    }

    @PostMapping("/best")
    public String sortedBestGames(Model model, @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size,
                                  Genre genre, Platform platform) {
        currentPage = page.orElse(1);
        pageSize = size.orElse(10);

        totalPages = countPages(gamesRepository, pageSize);

        games = getFilteredGames(genre, platform,
                PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("platforms", Platform.values());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("games", games);

        if (totalPages > 1) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }

        return "best";
    }

    @GetMapping("/soon")
    public String soonGames(Model model, @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        currentPage = page.orElse(1);
        pageSize = size.orElse(10);

        String curDate = LocalDate.now().toString();

        games = gamesRepository.findAllByReleaseDateAfter(curDate,
                PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("platforms", Platform.values());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("games", games);

        totalPages = countPages(gamesRepository, pageSize);

        if (totalPages > 1) {
            model.addAttribute("pageNumbers", getPageNumbers(totalPages));
        }
        return "soon";
    }

    private List<Integer> getPageNumbers(int size) {
        return IntStream.rangeClosed(1, size)
                .boxed()
                .collect(Collectors.toList());
    }

    private int countPages(GamesRepository gamesRepository, int pageSize) {
        return ((int) (gamesRepository.count() / pageSize)) + 1;
    }

    private Page<Game> getFilteredGames(Genre genre, Platform platform, Pageable pageable) {
        if (!genre.equals(Genre.ALL) && !platform.equals(Platform.ALL)) {
            return gamesRepository.findAllByGenreAndPlatform(genre, platform, pageable);
        }
        if (!genre.equals(Genre.ALL)) {
            return gamesRepository.findAllByGenre(genre, pageable);
        } else {
            if (!platform.equals(Platform.ALL)) {
                return gamesRepository.findAllByPlatform(platform, pageable);
            }
            return gamesRepository.findAll(pageable);
        }
    }
}
