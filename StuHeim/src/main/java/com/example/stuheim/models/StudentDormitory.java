package com.example.stuheim.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STUDENT_DORMITORIES")
public class StudentDormitory extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String dormitoryName;

    @Column(name = "address")
    private String dormitoryAddress;

    @Column(name = "city")
    private String dormitoryCity;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "student_capacity")
    private Long studentCapacity;

    @Column(name = "agreement_type")
    private String dormAgreementType;

    @Column(name = "party_room_in_dorm")
    private Boolean partyRoom;

    @Column(name = "lounge_room_in_dorm")
    private Boolean loungeRoom;

    @Column(name = "bezirk")
    private String bezirk;

    @Column(name = "roomTypes")
    private String roomTypes;

    @Column(name = "fitness_room_in_dorm")
    private Boolean fitnessRoom;

    @Column(name = "garage_in_dorm")
    private Boolean carGarage;

    @Column(name = "garage_monthly_cost")
    private Integer parkingCost;

    @Column(name = "garden_in_dorm")
    private Boolean garden;

    @Column(name = "kitchen_in_dorm")
    private Boolean communalKitchen;

    @Column(name = "clearning_room_in_dorm")
    private Boolean cleaningRoom;

    @Column(name = "nearby_universities")
    private String nearbyUniversities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentDormitory", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentDormitory", fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentDormitory", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

}
