package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.trainingapp.model.Todo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import dao.TodoDao;

public class TodoService {

	private TodoDao todoDao;
	
	public Map<String,String> storeTodo(Todo todo){
		Map<String,String> errors = new HashMap<String,String>();
		if(todo!=null && (StringUtils.isEmpty(todo.getTitle()) || todo.getTitle().length()>7)){
			errors.put("isError","true");
    		errors.put("title","Checked by server: between 1 and 6 letters");
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
