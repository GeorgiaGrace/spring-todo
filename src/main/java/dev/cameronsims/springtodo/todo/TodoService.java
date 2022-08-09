package dev.cameronsims.springtodo.todo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos( Long userId, boolean isComplete ) {

        System.out.println("UserId: " + userId + "Complete: " + isComplete);

        List<Todo> todos = todoRepository.findByUserIdAndIsComplete(userId, isComplete);

        Collections.sort( todos, ( item1, item2) -> {
            return (int) item1.getId() - (int) item2.getId();
        });

        return todos;

    }

    
    public void createTodo( Todo todo ) {

        todoRepository.save(todo);

    }

    public Todo updateTodo( long todoId, String task, boolean isComplete ) 
        throws NotFoundException, Exception{

        Todo todo = todoRepository.findById( todoId )
                        .orElseThrow( () -> new NotFoundException());

        todo.setTask(task);
        todo.setComplete(isComplete);

        todoRepository.save(todo);

        return todo;

    }

    public Todo updateTodoTask( long todoId, String task ) 
        throws NotFoundException, Exception{

        Todo targetTodo = todoRepository.findById(todoId).orElseThrow(() -> new NotFoundException());

        targetTodo.setTask(task);

        return todoRepository.save(targetTodo);

    }

    public Todo setTodoComplete( long todoId, boolean isComplete )
        throws NotFoundException {

        Todo todo = todoRepository.findById( todoId )
                        .orElseThrow( () -> new NotFoundException());

        todo.setComplete(isComplete);

        return todoRepository.save(todo);

    }

    public void deleteTodo( long todoId ) {
        todoRepository.deleteById(todoId);
    }
}
