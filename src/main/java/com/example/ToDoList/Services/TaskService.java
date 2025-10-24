package com.example.ToDoList.Services;

import com.example.ToDoList.Model.Task;
import com.example.ToDoList.Model.TaskDto;
import com.example.ToDoList.Model.Users;
import com.example.ToDoList.Repo.TaskRepo;
import com.example.ToDoList.Repo.UsersRepo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    TaskRepo repo;
    UsersRepo usersRepo;

    public TaskService(TaskRepo repo , UsersRepo usersRepo) {
        this.repo = repo;
        this.usersRepo = usersRepo;
    }



    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> tasks = (List<Task>) repo.findAll();
        List<TaskDto> taskDtos = new ArrayList<>();
        if(!tasks.isEmpty()){

            for(Task task : tasks){
                TaskDto taskDto = new TaskDto(task);
                taskDtos.add(taskDto);
            }

            return ResponseEntity.ok(taskDtos);
        }
        else return  ResponseEntity.noContent().build();
    }

    public ResponseEntity<TaskDto> getTask(Long id) {
        Optional<Task> task = repo.findById(id);
        if(task.isPresent()){
            TaskDto taskDto = new TaskDto(task.get());
            return ResponseEntity.ok(taskDto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Task> addTask(TaskDto dto) {
        System.out.println(dto);

        if(dto!=null){
            Task task = new Task();
            Users user = usersRepo.findById(dto.getUserid()).orElseThrow(()->new RuntimeException("USER NOT FOUND WITH ID " +dto.getUserid()));

            task.setTaskId(dto.getTaskId());
            task.setCategory(dto.getCategory());
            task.setDescription(dto.getDescription());
            task.setPriority(dto.getPriority());
            task.setTitle(dto.getTitle());
            task.setDueDate(dto.getDueDate());
            task.setIsCompleted(0);
            task.setUser(user);

            repo.save(task);
            return ResponseEntity.ok(task);
        }
        else return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Task> deleteTask(Long id) {
        Optional<Task> task = repo.findById(id);

        if (task.isPresent()){
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Task> updateTask(Long id, TaskDto dto) {

        Task existingTask = repo.findById(id).orElseThrow(()->new RuntimeException("NO TASK FOUND WITH ID "+ id));
        if(dto.getUserid()!=null) existingTask.getUser().setUserid(dto.getUserid());
        if(dto.getTaskId()!=null) existingTask.setTaskId(dto.getTaskId());
        if(dto.getCategory()!=null) existingTask.setCategory(dto.getCategory());
        if(dto.getDescription()!=null) existingTask.setDescription(dto.getDescription());
        if(dto.getTitle()!=null) existingTask.setTitle(dto.getTitle());
        if(dto.getDueDate()!= null) existingTask.setDueDate(dto.getDueDate());
        if(dto.getPriority()!=null) existingTask.setPriority(dto.getPriority());
        if(dto.getIsCompleted()!=null) existingTask.setIsCompleted(dto.getIsCompleted());

        repo.save(existingTask);
        return ResponseEntity.ok(existingTask);
    }
}
