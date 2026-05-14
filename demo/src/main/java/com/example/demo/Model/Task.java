package com.example.demo.Model;



import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity 
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String title ;
    private String description ;
    private TaskStatus status ;
    private Piriority piriority ;
    private Double budget ; 
    Date dueDate ;
       
    @JsonIgnore 
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;      
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "freelancer_id")
    private User freelancer;  

@JsonIgnore
@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
private List<TaskHistory> history;

public Task(Double budget,String description, Date dueDate, Long id, Piriority piriority, TaskStatus status, String title) {
   this.budget=budget;
    this.description = description;
    this.dueDate = dueDate;
    this.id = id;
    this.piriority = piriority;
    this.status = status;
    this.title = title;
}    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(User freelancer) {
        this.freelancer = freelancer;
    }

    public List<TaskHistory> getHistory() {
        return history;
    }
    public void setHistory(List<TaskHistory> history) {
        this.history = history;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
