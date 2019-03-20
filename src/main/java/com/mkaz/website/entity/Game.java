package com.mkaz.website.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String description;
    private String releaseDate;
    private String genre;
    private String platform;
}