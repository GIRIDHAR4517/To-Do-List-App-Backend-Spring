package com.example.ToDoList.Services;

import com.example.ToDoList.Model.Users;
import com.example.ToDoList.Repo.UsersRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UsersRepo repo;

    public UserService(UsersRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<Users> addUser(Users user) {
        if(user !=null){
            repo.save(user);
            return ResponseEntity.ok(user);
        }else return  ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = (List<Users>) repo.findAll();
        if(!users.isEmpty()) return  ResponseEntity.ok(users);
        else return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Users> getUser(Long id) {
        Users user = repo.findById(id).orElseThrow(()->new RuntimeException("USER NOT FOUND WITH ID "+id));
        return ResponseEntity.ok(user);
    }
}
