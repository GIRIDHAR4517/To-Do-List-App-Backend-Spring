package com.example.ToDoList.Controllers;

import com.example.ToDoList.Model.Users;
import com.example.ToDoList.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsersController {
    private UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<Users> addUser( @RequestBody Users user){
        return service.addUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){
        return service.getUser(id);
    }
}
