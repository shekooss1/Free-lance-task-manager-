package com.example.demo.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="task history")
public class TaskHistory {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)   
 private Long id ;
    private String changeDescription ;
    Date timeStamp ; //when the change happend

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

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


}
