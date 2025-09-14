package com.kefalas.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kefalas.taskmanager.models.Task;
import com.kefalas.taskmanager.models.User;
import com.kefalas.taskmanager.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task getTaskById(int id, String username) {
        return taskRepository.findByIdAndUserUsername(id, username).orElse(null);
    }

    public boolean deleteTask(int id, String username) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUserUsername(id, username);
        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
            return true;
        }
        return false;
    }

    public Task updateTask(int id, Task taskDetails, String username) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUserUsername(id, username);
        if (taskOptional.isPresent()) {
            taskDetails.setId(id);
            taskDetails.setUser(taskOptional.get().getUser());
            return taskRepository.save(taskDetails);
        }
        return null;
    }

    public Task createTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }
}
