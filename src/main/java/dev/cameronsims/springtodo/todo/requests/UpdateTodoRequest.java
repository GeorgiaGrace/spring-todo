package dev.cameronsims.springtodo.todo.requests;

public class UpdateTodoRequest {

    private String task;
    private boolean isComplete;

    public UpdateTodoRequest() {
    }

    public UpdateTodoRequest(String task, boolean isComplete ) {
        this.task = task;
        this.isComplete = isComplete;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

}
