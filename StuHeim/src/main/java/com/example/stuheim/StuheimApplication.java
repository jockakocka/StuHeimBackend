package com.example.stuheim;

import com.example.stuheim.models.Group;
import com.example.stuheim.models.Privilege;
import com.example.stuheim.models.User;
import com.example.stuheim.repositories.UserRepository;
import com.example.stuheim.services.GroupService;
import com.example.stuheim.services.PrivilegeService;
import com.example.stuheim.services.StudentDormitoryService;
import com.example.stuheim.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StuheimApplication {


    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {

        final UserRepository repository = repo;

        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> service = builder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = repository.findByUsername(username);
                List<Object[]> objects = repository.getPrivilegesByUser(user.getId());
                List<Privilege> privileges = new ArrayList<>();
                for(Object[] obj : objects){
                    Privilege privilege = new Privilege();
                    privilege.setId(  ((BigInteger)(obj[0])).longValue());
                    privilege.setName(  (String)    obj[5]);
                    privileges.add(privilege);
                }
                user.setPrivileges(privileges);
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                return customUserDetails;
            }
        });

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(service.getUserDetailsService());
        builder.authenticationProvider(authProvider);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer () {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
    public static String extractResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in;
        if(connection.getResponseCode() == 200)
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        else
        {
            in=new BufferedReader((new InputStreamReader(connection.getErrorStream())));
        }
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.equalsIgnoreCase("")){
                System.out.println("===========FRKA=============");
            }
            content.append(inputLine);
            content.append("\n");

        }
//		in.close();
        String response = content.toString();
        return response;
    }
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(StuheimApplication.class, args);
        UserService userService = (UserService) context.getBean("userService");
        GroupService groupService = (GroupService) context.getBean("groupService");
        PrivilegeService privilegeService = (PrivilegeService) context.getBean("privilegeService");
        StudentDormitoryService studentDormitoryService = (StudentDormitoryService) context.getBean("studentDormitoryService");

        Group administrators = groupService.findGroupByName("ADMINISTRATORS");
        if( administrators == null){
            Group group = new Group();
            group.setName("ADMINISTRATORS");
            administrators = groupService.save(group);
        }

        Group students = groupService.findGroupByName("STUDENTS");
        if(students == null){
            Group group2 = new Group();
            group2.setName("STUDENTS");
            students = groupService.save(group2);
        }

        if(privilegeService.findByName("ADMINISTRATION") == null){
            Privilege privilege = new Privilege();
            privilege.setName("ADMINISTRATION");
            privilegeService.save(privilege);
            privilegeService.addPrivilegeToGroup(privilege.getId(), administrators.getId());
        }
        if(privilegeService.findByName("STUDENT_UNION") == null){
            Privilege privilege = new Privilege();
            privilege.setName("STUDENT_UNION");
            privilegeService.save(privilege);
            privilegeService.addPrivilegeToGroup(privilege.getId(),students.getId());
        }

        User rootUser = userService.findUserByUsername("root");
        if(rootUser == null){
            rootUser = new User();
            rootUser.setUsername("root");
            rootUser.setPassword("pass123");
            rootUser.setName("Root");
            rootUser.setEmail("contact@aucta.dev");
            rootUser.setTelephone("0038975222213");
            rootUser = userService.save(rootUser);
            groupService.addUserToGroup(administrators.getId(), rootUser.getId());
        }

//        SpringApplication.run(StuheimApplication.class, args);
    }
}
