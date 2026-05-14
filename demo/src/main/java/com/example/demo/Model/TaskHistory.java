package com.example.demo.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="task history")
public class TaskHistory {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)   
 private Long id ;
    private String changeDescription ;
    Date timeStamp ; //when the change happend

   @ManyToOne 
   @JoinColumn(name="task_id")
   private Task task;
    
   
   @ManyToOne
    @JoinColumn(name = "performed_by_id")
    private User performedBy;
   
     private TaskStatus newStatus;  
    private String note; 
    public void setId(Long id) {
    this.id = id;
}
   public Task getTask() {
    return task;
   }
   public void setTask(Task task) {
    this.task = task;
   }
    public TaskHistory() {
    }
    public TaskHistory(String changeDescription, Long id, Date timeStamp) {
        this.changeDescription = changeDescription;
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChangeDescription() {
        return changeDescription;
    }

    public void setChangeDescription(String changeDescription) {
        this.changeDescription = changeDescription;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(java.util.Date timeStamp) {
        this.timeStamp = (Date) timeStamp;
    }
     public User getPerformedBy() { return performedBy; }
    public void setPerformedBy(User performedBy) { this.performedBy = performedBy; }

    public void setNewStatus(TaskStatus newStatus) {
    this.newStatus=newStatus;    
    }
    public void setNote(String note) {
    this.note=note;      
    }


}
