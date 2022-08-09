package dev.cameronsims.springtodo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.cameronsims.springtodo.security.auth.user.AppUserDetails;
import dev.cameronsims.springtodo.todo.Todo;
import dev.cameronsims.springtodo.todo.TodoService;


@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private TodoService todoService;

    @GetMapping()
    public String getApp(Model model) {

        long userId = ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        List<Todo> todos = todoService.getTodos(userId, false);

        model.addAttribute( "todos", todos );

        model.addAttribute("username", ( (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        return "app/app";

    }
    
}
