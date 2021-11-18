package com.example.stuheim.services;

import com.example.stuheim.models.Student;
import com.example.stuheim.models.User;
import com.example.stuheim.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public String getStudentByUsername(String username){
        User user = userRepository.findByUsername(username);
        Student student = user.getStudent();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", user.getName());
        jsonObject.put("surname", user.getSurname());
        jsonObject.put("username", user.getUsername());
        jsonObject.put("emailAddress", user.getEmail());
        jsonObject.put("phoneNumber", user.getTelephone());
        jsonObject.put("dateOfBirth", student.getDateOfBirth());
        jsonObject.put("dormitory", student.getStudentDormitory().getDormitoryName());
        jsonObject.put("university", student.getUniversity());
        jsonObject.put("degree", student.getStudyDegree());
        return jsonObject.toString();
    }

    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Page<User> getUsers(Pageable pageable, String username){
        return userRepository.findAllByUsernameContains(pageable, username);
    }

    public User getUser(Long id){
        return userRepository.getOne(id);
    }
    public boolean checkIfUserExist(String username){
        return userRepository.existsByUsername(username);
    }


    public User save(User user, UserDetails creator){
        if(user.getId() == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedBy(creator.getUsername());
        }else{
            User tmp = userRepository.getOne(user.getId());
            user.setGroups(tmp.getGroups());
            user.setPrivileges(tmp.getPrivileges());
        }
        user.setModifiedBy(creator.getUsername());
        return userRepository.save(user);
    }

    public User resetPassword(User user, UserDetails creator){
        User tmp = userRepository.findByUsername(user.getUsername());
        User tmp1 = user;
        user = tmp;
        user.setPassword(passwordEncoder.encode(tmp1.getPassword()));
        user.setModifiedBy(creator.getUsername());

        return userRepository.save(user);
    }

    public User save(User user){
        if(user.getId() == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedBy("system");
        }
        user.setModifiedBy("system");
        return userRepository.save(user);
    }

    public Boolean remove(Long id, UserDetails creator){
        User user = userRepository.getOne(id);
        user.setModifiedBy(creator.getUsername());
        userRepository.save(user);
        userRepository.delete(user);
        return Boolean.TRUE;
    }

}
