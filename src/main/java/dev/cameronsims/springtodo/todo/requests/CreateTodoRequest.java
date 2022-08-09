package dev.cameronsims.springtodo.todo.requests;

public class CreateTodoRequest {

    private String task;

    public CreateTodoRequest(String task) {
        this.task = task;
    }

    public CreateTodoRequest() {}

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}
