package com.example.stuheim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "RATINGS")
public class Rating extends AbstractEntity implements Serializable {

    @Column(name = "location_rating")
    private Integer locationRating;

    @Column(name = "price_rating")
    private Integer priceRating;

    @Column(name = "metro_rating")
    private Integer transportRating;

    @Column(name = "supermarket_rating")
    private Integer supermarketRating;

    @Column(name = "comfort_rating")
    private Integer comfortRating;

    @Column(name = "nearby_universities")
    private Integer universityRating;

    @Column(name = "neighbourhood_rating")
    private Integer neighbourhoodRating;

    @Column(name = "gym_rating")
    private Integer gymRating;

    @Column(name = "room_space_rating")
    private Integer roomSpaceRating;

    @Column(name = "internet_rating")
    private Integer internetRating;

    @Column(name = "kitchen_rating")
    private Integer kitchenRating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_dormitory_id")
    @JsonIgnore
    private StudentDormitory studentDormitory;
}
