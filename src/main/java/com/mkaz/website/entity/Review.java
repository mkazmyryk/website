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
    private String text;
    private Double rating;
    private String date;
    @OneToOne
    private User user;
}
