package com.example.stuheim.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STUDENTS")
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "university")
    private String university;

    @Column(name = "country")
    private String country;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "study_degree")
    private String studyDegree;

    @Column(name = "points")
    private Long points;

    @Column(name = "badge")
    private String badge;

    @Audited
    @OneToOne(mappedBy = "student")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_dormitory_id")
    @JsonIgnore
    private StudentDormitory studentDormitory;
}
