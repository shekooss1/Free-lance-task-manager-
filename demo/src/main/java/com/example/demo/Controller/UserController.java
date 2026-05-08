package com.example.demo.Controller;

import org.springframework.stereotype.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
            this file gets users or delete users only
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService; // took an obj

    // ─── REGISTER ───────────────────────────────────────────────
    // POST /api/users/register

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User created = userService.registerUser(user);
            return ResponseEntity.ok(created);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ─── LOGIN ──────────────────────────────────────────────────
    // POST /api/users/login?

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password)
    {
        try {
            User user = userService.loginUser(username, password);
            return ResponseEntity.ok(user);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    // ─── GET ALL USERS ──────────────────────────────────────────
    // GET /api/users

    @GetMapping
    public ResponseEntity<List<User> > getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ─── GET USER BY ID ─────────────────────────────────────────
    // GET /api/users/{id}

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById (@PathVariable Long id)
    {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // ─── DELETE USER ────────────────────────────────────────────
    // DELETE /api/users/{id}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully.\n");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}