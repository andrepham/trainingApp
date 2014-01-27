package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

public class TodoDao {

	private SessionFactory todoSessionFactory;
	
	@Required
	public void setTodoSessionFactory(SessionFactory todoSessionFactory) {
		this.todoSessionFactory = todoSessionFactory;
	}
}
