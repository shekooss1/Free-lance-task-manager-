package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TaskHistory;

@Repository
public interface  TaskHistoryRepository extends JpaRepository<TaskHistory,Long> {

}
