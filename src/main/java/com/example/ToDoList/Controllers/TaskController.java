package com.example.ToDoList.Controllers;

import com.example.ToDoList.Model.Task;
import com.example.ToDoList.Model.TaskDto;
import com.example.ToDoList.Services.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {
    TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasks(){

        return service.getTasks();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id){
        return service.getTask(id);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto dto){
        return service.addTask(dto);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        return service.deleteTask(id);

    }

    @PatchMapping("/task/{id}")
    public  ResponseEntity<Task> updateTask(@PathVariable Long id , @RequestBody TaskDto dto){
        return service.updateTask(id , dto);

    }
}
