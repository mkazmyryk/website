package com.mkaz.website.entity;

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
    private String genre;
    private String platform;
    private String videoLink;
    private String imageLink;
    private Double avrRating;

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    public Double getAverageRating() {
        return avrRating;
    }

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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}