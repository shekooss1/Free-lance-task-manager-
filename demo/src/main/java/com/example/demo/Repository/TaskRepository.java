package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;
import com.example.demo.Model.TaskStatus;

@Repository
public interface  TaskRepository extends JpaRepository<Task,Long> {

    public List<Task> findByFreelancerId(Long freelancerId);

    public List<Task> findByClientId(Long clientId);

    public List<Task> findByStatus(TaskStatus taskStatus);

}
