package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Task;
import com.example.demo.Model.TaskStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task createTask(Task task, Long clientId) {
        User client = userService.getUserById(clientId);
        task.setClient(client);
        task.setStatus(TaskStatus.OPEN); // ✅ fixed
        return taskRepository.save(task);
    }

    public Task acceptTask(Long taskId, Long freelancerId) {
        Task task = getTaskById(taskId);

        if (task.getStatus() != TaskStatus.OPEN) { // ✅ fixed
            throw new RuntimeException("Task is not available — current status: " + task.getStatus());
        }

        User freelancer = userService.getUserById(freelancerId);
        task.setFreelancer(freelancer);
        task.setStatus(TaskStatus.IN_PROGRESS); // ✅ fixed
        return taskRepository.save(task);
    }

    public Task completeTask(Long taskId) {
        Task task = getTaskById(taskId);

        if (task.getStatus() != TaskStatus.IN_PROGRESS) { // ✅ fixed
            throw new RuntimeException("Only IN_PROGRESS tasks can be completed.");
        }

        task.setStatus(TaskStatus.COMPLETED); // ✅ fixed
        return taskRepository.save(task);
    }

    public Task cancelTask(Long taskId) {
        Task task = getTaskById(taskId);

        if (task.getStatus() == TaskStatus.COMPLETED) { // ✅ fixed
            throw new RuntimeException("Cannot cancel a completed task.");
        }

        task.setStatus(TaskStatus.CANCELLED);
        return taskRepository.save(task);
    }

    public List<Task> getOpenTasks() {
        return taskRepository.findByStatus(TaskStatus.OPEN); // ✅ fixed
    }

    public List<Task> getTasksByClient(Long clientId) {
        return taskRepository.findByClientId(clientId);
    }

    public List<Task> getTasksByFreelancer(Long freelancerId) {
        return taskRepository.findByFreelancerId(freelancerId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Cannot delete — task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}