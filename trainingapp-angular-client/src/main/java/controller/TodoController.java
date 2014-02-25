package controller;

import model.Todo;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.TodoService;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Controller
public class TodoController {

    private TodoService todoService;

	@RequestMapping(value = "/todo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Todo> list() {
		return todoService.retrieveTodos();
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Todo getById(@PathVariable long id) {
        return todoService.retrieveTodoById(id);
    }
    
    @RequestMapping(value = "/todo", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody Map<String,String> save(@RequestBody Todo todo) {
        return todoService.storeTodo(todo);
    }
    
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }
    
    @Required
 	public void setTodoService(TodoService todoService) {
 		this.todoService = todoService;
 	}

}