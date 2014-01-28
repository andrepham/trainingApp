package dao;

import model.Todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Required;

import store.model.User;

public class TodoDao {

	private SessionFactory todoSessionFactory;
	
	public void store(Todo todo){
		Session session = todoSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
		tx.begin();
		session.merge(todo);
		tx.commit();
		}
		catch(RuntimeException re){
			tx.rollback();
			throw re;
		}
		finally{
			session.close();
		}
	}
	
	@Required
	public void setTodoSessionFactory(SessionFactory todoSessionFactory) {
		this.todoSessionFactory = todoSessionFactory;
	}
}
