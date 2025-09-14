package com.kefalas.taskmanager.repositories;

import com.kefalas.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUserUsername(String username);
    Optional<Task> findByIdAndUserUsername(int id, String username);
}
