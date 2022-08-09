package dev.cameronsims.springtodo.todo.responses;

public class DeleteTodoResponse {

    private boolean success;
    private long deleteId;

    public DeleteTodoResponse() {
    }

    public DeleteTodoResponse(boolean success, long deleteId) {
        this.success = success;
        this.deleteId = deleteId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(long deleteId) {
        this.deleteId = deleteId;
    }
    
}
