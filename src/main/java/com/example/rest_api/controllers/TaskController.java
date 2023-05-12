package com.example.rest_api.controllers;

import com.example.rest_api.entities.Task;
import com.example.rest_api.entities.User;
import com.example.rest_api.services.TaskService;
import com.example.rest_api.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/assigner/{username}")
    public ResponseEntity<List<Task>> getTasksByAssigner(@PathVariable String username) {
        Optional<List<Task>> tasks = taskService.findByAssigner(username);
        if (tasks.isPresent()) {
            return ResponseEntity.ok(tasks.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/assignee/{username}")
    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable String username) {
        Optional<List<Task>> tasks = taskService.findByAssignee(username);
        if (tasks.isPresent()) {
            return ResponseEntity.ok(tasks.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/assigner/{username}/{status}")
    public ResponseEntity<List<Task>> getTasksByAssignerAndStatus(@PathVariable String username, @PathVariable Status status) {
        Optional<List<Task>> tasks = taskService.findByAssignerAndStatus(username, status);
        if (tasks.isPresent()) {
            return ResponseEntity.ok(tasks.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<List<Task>> getTasksByAgent(@PathVariable User id) {
        Optional<List<Task>> tasks = taskService.findByAgent(id);
        if (tasks.isPresent()) {
            return ResponseEntity.ok(tasks.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/responsible")
    public ResponseEntity<Task> assignTaskToResponsible(@RequestBody Task task) {
        Task assignedTask = taskService.assignTaskToResponsible(task);
        return ResponseEntity.ok(assignedTask);
    }

    @PostMapping("/officer")
    public ResponseEntity<Task> assignTaskToOfficer(@RequestBody Task task) {
        Task assignedTask = taskService.assignTaskToOfficer(task);
        return ResponseEntity.ok(assignedTask);
    }

}
