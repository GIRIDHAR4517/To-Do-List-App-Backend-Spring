package com.example.ToDoList.Repo;

import com.example.ToDoList.Model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<Users , Long> {
}
