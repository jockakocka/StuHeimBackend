package com.example.stuheim.controllers;

import com.example.stuheim.models.Rating;
import com.example.stuheim.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/ratings")
public class RatingController {

    final
    RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public ResponseEntity<Rating> createRating(@PathVariable Long id, @RequestBody Rating rating,@RequestParam String username){
        return new ResponseEntity<>(ratingService.createRating(id, rating,username), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Rating>> getAllRatings(@PathVariable Long studentDormId){
        return new ResponseEntity<>(ratingService.findAllRatings(studentDormId), HttpStatus.OK);
    }
}
