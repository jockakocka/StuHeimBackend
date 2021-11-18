package com.example.stuheim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ROOMS")
public class Room extends AbstractEntity implements Serializable {

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_size")
    private Float roomSize;

    @Column(name = "room_floor")
    private Long roomFloor;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "bathroom_in_room")
    private Boolean bathroom;

    @Column(name = "internet_in_room")
    private Boolean internet;

    @Column(name = "kitchenette_in_room")
    private Boolean kitchenette;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_dormitory_id")
    @JsonIgnore
     private StudentDormitory studentDormitory;

}
