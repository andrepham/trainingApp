package service;

import org.springframework.beans.factory.annotation.Required;

import dao.TodoDao;
import model.Todo;

public class TodoService {

	private TodoDao todoDao;
	
	public void storeTodo(Todo todo){
		
	}

	@Required
	public void setTodoDao(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
	
	
}
