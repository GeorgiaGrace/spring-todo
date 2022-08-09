package dev.cameronsims.springtodo.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cameronsims.springtodo.security.auth.user.AppUserDetails;
import dev.cameronsims.springtodo.todo.requests.CreateTodoRequest;
import dev.cameronsims.springtodo.todo.requests.UpdateTodoRequest;
import dev.cameronsims.springtodo.todo.responses.DeleteTodoResponse;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping()
    public List<Todo> getTodos() {

        long userId = ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        return todoService.getTodos(userId, false);

    }

    @PostMapping()
    public Todo createTodo( @RequestBody CreateTodoRequest request ) {

        long userId = ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        Todo todo = new Todo(userId, request.getTask(), false);

        todoService.createTodo(todo);

        return todo;

    }

    @PatchMapping("/{todoId}")
    public Todo updateTodo(@PathVariable(value="todoId") Long todoId, @RequestBody UpdateTodoRequest request) throws NotFoundException, Exception {

        long userId = ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        Todo targetTodo = todoRepository.findById( todoId )
                        .orElseThrow( () -> new NotFoundException());

        if (userId != targetTodo.getUserId()) {
            throw new Exception("You may not edit another user's todos.");
        }
                
        Todo todo = todoService.updateTodo( todoId, request.getTask(), request.isComplete());

        return todo;

    }
    
    @DeleteMapping("/{todoId}")
    public DeleteTodoResponse deleteTodo(@PathVariable(value="todoId") Long todoId) throws NotFoundException, Exception {
            
        long userId = ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        Todo targetTodo = todoRepository.findById( todoId )
                        .orElseThrow( () -> new NotFoundException());

        if (userId != targetTodo.getUserId()) {
            throw new Exception("You may not edit another user's todos.");
        }

        todoService.deleteTodo(todoId);

        return new DeleteTodoResponse(true, todoId);

    }

}
