package com.example.demo.Model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
    private String name ;
    private Long phone ;
    private String email ;
    private String password ;
    Role role ;
    Date joinedDate;

@JsonIgnore
    @OneToMany(mappedBy = "client")
private List<Task> clientTasks;    // tasks where this user is the CLIENT

@JsonIgnore
@OneToMany(mappedBy = "freelancer")
private List<Task> freelancerTasks; // tasks where this user is the FREELANCER
    
public User() {
    }

    public User(String email, Long id, Date joinedDate, String name, String password, Long phone, Role role) {
        this.email = email;
        this.id = id;
        this.joinedDate = joinedDate;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public List<Task> getClientTasks() {
        return clientTasks;
    }

    public void setClientTasks(List<Task> clientTasks) {
        this.clientTasks = clientTasks;
    }

    public List<Task> getFreelancerTasks() {
        return freelancerTasks;
    }

    public void setFreelancerTasks(List<Task> freelancerTasks) {
        this.freelancerTasks = freelancerTasks;
    }



}
