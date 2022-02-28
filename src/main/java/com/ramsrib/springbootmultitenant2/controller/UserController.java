package com.ramsrib.springbootmultitenant2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map; 

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<?> listUsers() throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("users", userService.listUsers());
    String response = new ObjectMapper().writeValueAsString(map);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable("id") long userId) throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("user", userService.getUser(userId));
    String response = new ObjectMapper().writeValueAsString(map);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") long userId) { 
    userService.deleteUser(userId);
  }

}
