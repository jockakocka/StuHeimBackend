package com.example.stuheim.controllers;

import com.example.stuheim.models.Student;
import com.example.stuheim.models.StudentDormitory;
import com.example.stuheim.services.StudentDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentDormitories")
@Component
public class StudentDormitoryContoller {

    private final StudentDormitoryService studentDormitoryService;

    public StudentDormitoryContoller(StudentDormitoryService studentDormitoryService) {
        this.studentDormitoryService = studentDormitoryService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDormitory> getStudentDormitory(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(studentDormitoryService.getStudentDorm(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<StudentDormitory> createStudentDorm(@RequestBody StudentDormitory studentDormitory) {
        return new ResponseEntity<>(studentDormitoryService.createStudentDorm(studentDormitory), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<StudentDormitory> updateStudent(@RequestBody StudentDormitory studentDormitory) {
        return new ResponseEntity<>(studentDormitoryService.updateStudentDorm(studentDormitory), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteStudentDormitory(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(studentDormitoryService.deleteStudentDorm(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Page<StudentDormitory> getAll(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        return (studentDormitoryService.getAllStudentDormitories(PageRequest.of(page, size)));
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDormitory> getDormByStudent(@PathVariable(value = "id") Long studentId){
        return new ResponseEntity<>(studentDormitoryService.getDormitoryByStudent(studentId), HttpStatus.OK);
    }



}
