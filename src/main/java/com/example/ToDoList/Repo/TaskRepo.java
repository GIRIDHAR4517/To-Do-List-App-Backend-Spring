package com.example.ToDoList.Repo;

import com.example.ToDoList.Model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task , Long> {
}
