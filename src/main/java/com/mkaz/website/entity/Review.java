package com.mkaz.website.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String review;
    private Double rating;
    @OneToOne
    private User user;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }

    public Double getRating() {
        return rating;
    }
}
