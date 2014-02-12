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
	
	public Map<String, String> validate(Todo toValidate){
		Map<String,String> errors = new HashMap<String,String>();
		if(toValidate!=null && (StringUtils.isEmpty(toValidate.getTitle()) || toValidate.getTitle().length()>7)){
    		errors.put("title","Checked by server: between 1 and 6 letters");
    	}
		return errors;
	}
	
	public void storeTodo(Todo todo){
		todoDao.store(todo);
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
