package com.example.stuheim.services;

import com.example.stuheim.models.Student;
import com.example.stuheim.models.StudentDormitory;
import com.example.stuheim.repositories.StudentDormitoryRepository;
import com.example.stuheim.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentDormitoryService {

    final
    StudentDormitoryRepository studentDormitoryRepository;

    @Autowired
    StudentRepository studentRepository;

    public StudentDormitoryService(StudentDormitoryRepository studentDormitoryRepository) {
        this.studentDormitoryRepository = studentDormitoryRepository;
    }

    public StudentDormitory getStudentDorm(Long id){
        StudentDormitory studentDormitory = studentDormitoryRepository.findById(id).get();
        return studentDormitory;
    }

    public Page<StudentDormitory> getAllStudentDormitories(Pageable pageable){
        return studentDormitoryRepository.findAll(pageable);
    }

    public StudentDormitory createStudentDorm(StudentDormitory studentDormitory){
        return studentDormitoryRepository.save(studentDormitory);
    }

    public StudentDormitory updateStudentDorm(StudentDormitory studentDormitory){
        return studentDormitoryRepository.save(studentDormitory);
    }

//    public StudentDormitory findDormitoryByName(String name){
//        return studentDormitoryRepository.findByName(name);
//    }

    public Boolean deleteStudentDorm(Long id){
        StudentDormitory studentDormitory = studentDormitoryRepository.findById(id).get();
        studentDormitoryRepository.save(studentDormitory);
        studentDormitoryRepository.delete(studentDormitory);
        return Boolean.TRUE;
    }

    public StudentDormitory getDormitoryByStudent(Long studentId){
        Student student = studentRepository.findById(studentId).get();
        return student.getStudentDormitory();
    }
}
