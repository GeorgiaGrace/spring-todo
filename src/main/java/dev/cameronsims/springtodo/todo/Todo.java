package dev.cameronsims.springtodo.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Todo {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "todo_sequence"
    )
    @SequenceGenerator(
        name = "todo_sequence",
        sequenceName = "todo_sequence",
        allocationSize = 1
    )
    private long id;
    private long userId;
    private String task;
    private boolean isComplete;

    public Todo( long id, long userId, String task, boolean isComplete ) {
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.isComplete = isComplete;
    }

    public Todo( long userId, String task, boolean isComplete ) {
        this.userId = userId;
        this.task = task;
        this.isComplete = isComplete;
    }

    public Todo () {}

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    
    
}
