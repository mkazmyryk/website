package com.mkaz.topgames.entity;

import com.mkaz.topgames.service.ColorService;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple POJO class for game representation.
 *
 * @author mkazmyryk
 * @version 1.0
 */
@Data
@Entity
@Table(name = "games")
public class Game {
    /**
     * Contains id
     */
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String releaseDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Platform platform;
    private String videoLink;
    private String imageLink;
    private Double avrRating;

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
        Double sum = 0.0;
        if (reviews.size() == 0) {
            avrRating = 0.0;
        } else {
            for (Review tempReview : reviews) {
                sum += tempReview.getRating();
            }
            avrRating = sum / reviews.size();
        }
    }

    public String getColor() {
        return ColorService.getColor(avrRating);
    }

}