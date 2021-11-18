package com.example.stuheim.controllers;


import com.example.stuheim.CustomUserDetails;
import com.example.stuheim.models.User;
import com.example.stuheim.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Component
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 60000)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public CustomUserDetails getUser(OAuth2Authentication authentication){
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        return details;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Page<User> getAllUsers(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter", required = false) String filter
    ) {

        return userService.getUsers(PageRequest.of(page, size), filter);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<String> getStudentByUsername(@RequestParam(value = "username", required = false) String username){
        return new ResponseEntity<>(userService.getStudentByUsername(username), HttpStatus.OK);
    }

    @RequestMapping(value="/user/{userName}",method = RequestMethod.GET)
    public boolean checkIfUserExists(
            @PathVariable(value="userName") String userName
    )
    {
        return userService.checkIfUserExist(userName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") Long id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User createUser(@RequestBody User user, OAuth2Authentication authentication) {
        if(authentication != null) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            return userService.save(user, details);
        }
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, OAuth2Authentication authentication) {
        if(authentication != null) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            return userService.save(user, details);
        }
        return null;
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.PUT)
    public User resetPassword(@RequestBody User user, OAuth2Authentication authentication) {
        if(authentication != null) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            return userService.resetPassword(user, details);
        }
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Boolean deleteUser(@PathVariable(value = "id") Long id, OAuth2Authentication authentication) {
        if(authentication != null) {
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            return userService.remove(id, details);
        }
        return null;
    }
}
