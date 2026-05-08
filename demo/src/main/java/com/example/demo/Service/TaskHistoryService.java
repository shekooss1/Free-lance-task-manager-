package com.example.demo.Service;

import com.example.demo.Model.Task;
import com.example.demo.Model.TaskHistory;
import com.example.demo.Model.TaskStatus;
import com.example.demo.Model.User;
import com.example.demo.Repository.TaskHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

   public TaskHistory recordChange(Task task, User performedBy, 
                                 TaskStatus newStatus, String note) {
    TaskHistory entry = new TaskHistory();
    entry.setTask(task);
    entry.setPerformedBy(performedBy);
    entry.setNewStatus(newStatus);
    entry.setNote(note);
    entry.setTimeStamp(new Date(System.currentTimeMillis()));
    return taskHistoryRepository.save(entry);
}

   
    public List<TaskHistory> getHistoryForTask(Long taskId) {
        return taskHistoryRepository.findByTaskIdOrderByTimeStampAsc(taskId);
    }

    public List<TaskHistory> getHistoryByUser(Long userId) {
        return taskHistoryRepository.findByPerformedById(userId);
    }

    
    public List<TaskHistory> getAllHistory() {
        return taskHistoryRepository.findAll();
    }
}