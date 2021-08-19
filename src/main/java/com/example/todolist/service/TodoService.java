package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodoDone(Integer id, Todo todoInfo) {
        Todo updateTodo = todoRepository.findById(id)
                .map(todo -> updateTodoInfo(todo, todoInfo))
                .get();
        return todoRepository.save(updateTodo);
    }

//    public Todo updateTodoDone(Integer id, Todo todoInfo) {
//        Todo updateTodoDone = todoRepository.findById(id).orElse(null);
//        updateTodoDone.setDone(todoInfo.getDone());
//        return todoRepository.save(updateTodoDone);
//    }

//    public Todo updateTodoText(Integer id, Todo todoInfo) {
//        Todo updateTodoText = todoRepository.findById(id)
//                .map(todo -> updateTodoTextInfo(todo, todoInfo))
//                .get();
//        return todoRepository.save(updateTodoText);
//    }

    public Todo removeTodo(Integer id) {
        Optional<Todo> removeTodo = todoRepository.findById(id);
        todoRepository.deleteById(id);
        return removeTodo.orElse(null);
    }

    private Todo updateTodoInfo(Todo todo, Todo todoInfo) {
        if(todo.getDone() != todoInfo.getDone()){
            todo.setDone(todoInfo.getDone());
        }
        if (todoInfo.getText() != null){
            todo.setText(todoInfo.getText());
        }
        return todo;
    }

}
