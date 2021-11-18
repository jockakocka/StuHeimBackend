package com.example.stuheim.services;

import com.example.stuheim.models.Student;
import com.example.stuheim.models.StudentDormitory;
import com.example.stuheim.models.User;
import com.example.stuheim.repositories.StudentDormitoryRepository;
import com.example.stuheim.repositories.StudentRepository;
import com.example.stuheim.repositories.UserRepository;
import net.bytebuddy.implementation.StubMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    final
    StudentRepository studentRepository;

    final
    StudentDormitoryRepository studentDormitoryRepository;

    final
    UserRepository userRepository;

    final
    UserService userService;

    public StudentService(StudentRepository studentRepository, StudentDormitoryRepository studentDormitoryRepository,
                          UserRepository userRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.studentDormitoryRepository = studentDormitoryRepository;
        this.userRepository=userRepository;
        this.userService = userService;
    }

    public Page<Student> getAllStudents(Pageable pageable){
        return studentRepository.findAll(pageable);
    }

    public String getStudentSorted(){
        List<User> users = userRepository.findAll();
        User temp= new User();
        for(int i=0; i < users.size(); i++){
            for(int j=1; j < (users.size()-i); j++){
                if(users.get(j-1).getStudent().getPoints() < users.get(j).getStudent().getPoints())
                {
                    //swap elements
                    temp = users.get(j-1);
                    users.set(j-1,users.get(j));
                    users.set(j,temp);
                }

            }
        }
        JSONArray jsonArray = new JSONArray();
        for(User user: users){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName", user.getName());
            jsonObject.put("surname", user.getSurname());
            jsonObject.put("studentDormitory", user.getStudent().getStudentDormitory().getDormitoryName());
            jsonObject.put("points", user.getStudent().getPoints());
            jsonObject.put("badge", user.getStudent().getBadge());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    public Student getStudent(Long id){
        return studentRepository.findById(id).get();
    }

   public Student createStudent(Student student){
        return studentRepository.save(student);
   }

   public Student updateStudent(Student student){
        return studentRepository.save(student);
   }

    public Boolean deleteStudent(Long id){
        Student student = studentRepository.findById(id).get();
        studentRepository.save(student);
        studentRepository.delete(student);
        return Boolean.TRUE;
    }

    public List<Student> getAllStudentsInDorm(Long studentDormId){
        StudentDormitory studentDormitory = studentDormitoryRepository.findById(studentDormId).get();
        return studentDormitory.getStudents();
    }

    public String saveUserAsStudent(String information){

        JSONObject jsonObject = new JSONObject(information);
        User user = new User();
        Student student = new Student();
        String dormitoryName = jsonObject.getString("studentDormitory");
//        StudentDormitory studentDormitory = studentDormitoryRepository.findByName(dormitoryName);
        List<Student> students = new ArrayList<>();

        user.setName(jsonObject.getString("name"));
        user.setSurname(jsonObject.getString("surname"));
        user.setEmail(jsonObject.getString("email"));
        user.setTelephone(jsonObject.getString("telephone"));
        user.setPassword(jsonObject.getString("password"));
        user.setUsername(jsonObject.getString("username"));

        student.setId(jsonObject.getLong("studentId"));
//        student.setDateOfBirth(jsonObject.getString("dateOfBirth"));
        student.setNationality(jsonObject.getString("nationality"));
//        student.setStudentDormitory(studentDormitory);
        student.setUniversity(jsonObject.getString("university"));
        student.setStudyDegree(jsonObject.getString("studyDegree"));

        user.setStudent(student);
        student.setUser(user);
        students.add(student);
//        studentDormitory.setStudents(students);

        userService.save(user);
        studentRepository.save(student);
        System.out.println(information);
        return information;
    }
}
