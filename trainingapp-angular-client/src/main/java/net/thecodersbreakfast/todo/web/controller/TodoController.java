package net.thecodersbreakfast.todo.web.controller;

import model.Todo;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.TodoService;
import store.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Olivier Croisier
 */
@Controller
public class TodoController {

    private static final AtomicLong todoIdGenerator = new AtomicLong(0);
    private static final ConcurrentSkipListMap<Long, Todo> todoRepository = new ConcurrentSkipListMap<Long, Todo>();
    private TodoService todoService;

	@RequestMapping(value = "/todo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Todo> list() {
        return new ArrayList<Todo>(todoRepository.values());
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Todo getById(@PathVariable long id) {
        return todoRepository.get(id);
    }

    @RequestMapping(value = "/todo", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Todo todo) {
        long id = todoIdGenerator.incrementAndGet();
        todo.setId(id);
        todoRepository.put(id, todo);
        todoService.storeTodo(null);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        todoRepository.remove(id);
    }
    
    @Required
 	public void setTodoService(TodoService todoService) {
 		this.todoService = todoService;
 	}

}
