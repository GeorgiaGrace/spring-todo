package dev.cameronsims.springtodo.todo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    //List<Todo> findAllByUserIdAndIsCompelete(Long userId, boolean isComplete);

    List<Todo> findByUserIdAndIsComplete(Long userId, Boolean isComplete);
    
}
