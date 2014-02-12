package service;

import java.util.List;

import net.trainingapp.model.Todo;

import org.springframework.beans.factory.annotation.Required;

import dao.TodoDao;

public class TodoService {

	private TodoDao todoDao;
	
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
