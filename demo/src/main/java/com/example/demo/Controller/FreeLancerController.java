package com.example.demo.Controller;

import com.example.demo.Model.Task;
import com.example.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancer")
@CrossOrigin(origins = "*")
public class FreeLancerController {

    @Autowired
    private TaskService taskService;

    // See all open tasks available to pick up
    @GetMapping("/tasks/open")
    public ResponseEntity<List<Task>> getOpenTasks() {
        return ResponseEntity.ok(taskService.getOpenTasks());
    }

    // Accept a task
    @PutMapping("/tasks/{taskId}/accept")
    public ResponseEntity<?> acceptTask(@PathVariable Long taskId,
                                        @RequestParam Long freelancerId) {
        try {
            Task updated = taskService.acceptTask(taskId, freelancerId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mark task as complete
    @PutMapping("/tasks/{taskId}/complete")
    public ResponseEntity<?> completeTask(@PathVariable Long taskId) {
        try {
            Task updated = taskService.completeTask(taskId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // See my assigned tasks
    @GetMapping("/tasks/{freelancerId}")
    public ResponseEntity<List<Task>> getMyTasks(@PathVariable Long freelancerId) {
        return ResponseEntity.ok(taskService.getTasksByFreelancer(freelancerId));
    }
}