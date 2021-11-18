package com.example.stuheim.services;

import com.example.stuheim.models.Rating;
import com.example.stuheim.models.Student;
import com.example.stuheim.models.StudentDormitory;
import com.example.stuheim.models.User;
import com.example.stuheim.repositories.RatingRepository;
import com.example.stuheim.repositories.StudentDormitoryRepository;
import com.example.stuheim.repositories.StudentRepository;
import com.example.stuheim.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingService {

    final
    RatingRepository ratingRepository;
    final UserRepository userRepository;
    final StudentRepository studentRepository;

    final
    StudentDormitoryRepository studentDormitoryRepository;

    public RatingService(RatingRepository ratingRepository, StudentDormitoryRepository studentDormitoryRepository,
                         UserRepository userRepository,StudentRepository studentRepository) {
        this.ratingRepository = ratingRepository;
        this.studentDormitoryRepository = studentDormitoryRepository;
        this.userRepository=userRepository;
        this.studentRepository=studentRepository;
    }

    public List<Rating> findAllRatings(Long studentDormId){
        StudentDormitory studentDormitory = studentDormitoryRepository.getOne(studentDormId);
        List<Rating> ratingList;
        ratingList = studentDormitory.getRatings();
        return ratingList;
    }

    public Rating createRating(Long studentDormId, Rating rating,String username)
    {
        User student=userRepository.findByUsername(username);
        Student studentUser=student.getStudent();
        studentUser.setPoints(studentUser.getPoints()+10);
        studentRepository.save(studentUser);
        StudentDormitory studentDormitory = studentDormitoryRepository.getOne(studentDormId);
        rating.setStudentDormitory(studentDormitory);
        return ratingRepository.save(rating);
    }

}
