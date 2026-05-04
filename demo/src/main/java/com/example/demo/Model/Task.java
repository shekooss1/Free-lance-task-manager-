package com.example.demo.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String title ;
    private String description ;
    private TaskStatus status ;
    private Piriority piriority ;
    Date dueDate ;
@ManyToOne @JoinColumn(name = "user_id") private User user;
    public Task(String description, Date dueDate, long id, Piriority piriority, TaskStatus status, String title) {
        this.description = description;
        this.dueDate = dueDate;
        this.id = id;
        this.piriority = piriority;
        this.status = status;
        this.title = title;
    }

    public Task() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Piriority getPiriority() {
        return piriority;
    }

    public void setPiriority(Piriority piriority) {
        this.piriority = piriority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
