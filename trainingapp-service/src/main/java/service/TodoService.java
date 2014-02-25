package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Todo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import dao.TodoDao;

public class TodoService {

	private TodoDao todoDao;
	
	public Map<String,String> storeTodo(Todo todo){
		Map<String,String> errors = new HashMap<String,String>();
		if(todo!=null && (StringUtils.isEmpty(todo.getTitle()) || todo.getTitle().length()<2 || todo.getTitle().length()>6)){
    		errors.put("title","Checked by server: between 2 and 6 letters");
    	}
		if(todo!=null && StringUtils.isEmpty(todo.getDescription()) || todo.getDescription().length()<2 || todo.getDescription().length()>6){
			errors.put("description"," Checked by server: between 2 and 6 letters");
		}
		if(errors.isEmpty()){
			todoDao.store(todo);
		}
		return errors;
	}
	
	public List<Todo> retrieveTodos(){
		return todoDao.retrieveTodos();
	}

	public Todo retrieveTodoById(long id){
		return todoDao.retrieveTodoById(id);
	}
	
	public void deleteTodoById(long id){
		todoDao.deleteTodoById(id);
	}
	
	@Required
	public void setTodoDao(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
}
