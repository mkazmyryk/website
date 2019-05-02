package com.mkaz.website.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    private LocalDateTime date;
    @OneToOne
    private User user;

    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public String getColor() {
        if (rating <= 5) {
            return "red";
        } else {
            if (rating > 5 && rating <= 8) return "#eaec00";
        }
        return "lawngreen";
    }
}
