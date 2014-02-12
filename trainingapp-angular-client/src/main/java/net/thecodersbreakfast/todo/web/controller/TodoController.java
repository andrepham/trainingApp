package net.thecodersbreakfast.todo.web.controller;

import model.Todo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import service.TodoService;

import java.util.HashMap;
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
    
    @RequestMapping(value = "/todo", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Todo todo) {
        todoService.storeTodo(todo);
    }
    
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }
    
    @RequestMapping(value = "/todo/validate", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> validate(@RequestBody Todo toValidate) {
    	Map<String,String> errors = new HashMap<String,String>();
    	if(toValidate!=null && (StringUtils.isEmpty(toValidate.getTitle()) || toValidate.getTitle().length()<10)){
    		errors.put("title","Need more than 10 characters");
    	}
    	return errors;
    }

    @Required
 	public void setTodoService(TodoService todoService) {
 		this.todoService = todoService;
 	}

}