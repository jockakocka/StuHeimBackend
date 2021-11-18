package com.example.stuheim.controllers;

import com.example.stuheim.CustomUserDetails;
import com.example.stuheim.models.Student;
import com.example.stuheim.models.User;
import com.example.stuheim.services.StudentService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/students")
@Component
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteStudent(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Page<Student> getAll(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        return (studentService.getAllStudents(PageRequest.of(page, size)));
    }

    @RequestMapping(value = "/sorted", method = RequestMethod.GET)
    public ResponseEntity<String> getSortedStudents(){
        return new ResponseEntity<>(studentService.getStudentSorted(), HttpStatus.OK);
    }

    @RequestMapping(value = "/dorm/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudentsByDorm(@PathVariable(value = "id") Long studentDormitoryId){
        return new ResponseEntity<>(studentService.getAllStudentsInDorm(studentDormitoryId), HttpStatus.OK);
    }

    @RequestMapping(value = "/register_new", method = RequestMethod.POST)
    public ResponseEntity<String> saveUserAsStudent(@RequestBody String requestBody){
        return new ResponseEntity<>(studentService.saveUserAsStudent(requestBody), HttpStatus.OK);
    }

}
