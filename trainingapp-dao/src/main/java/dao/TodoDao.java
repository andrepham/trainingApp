package dao;

import java.util.List;

import model.Todo;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Required;

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
	
	public List<Todo> retrieveTodos(){
		Session session = todoSessionFactory.openSession();
		List<Todo> todos = session.createCriteria(Todo.class).list();
		session.close();
		return todos;
	}
	
	public Todo retrieveTodoById(long id){
		Session session = todoSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Todo todo = null;
		try{
			tx.begin();
			todo = (Todo)session.get(Todo.class, id);
			tx.commit();
		}
		catch(RuntimeException re){
			tx.rollback();
			throw re;
		}
		finally{
			session.close();
		}
		return todo; 
	}
	
	public Todo deleteTodoById(long id){
		Session session = todoSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Todo todo = null;
		try{
			tx.begin();
			todo = (Todo)session.load(Todo.class, id);
			session.delete(todo);
			tx.commit();
		}
		catch(RuntimeException re){
			tx.rollback();
			throw re;
		}
		finally{
			session.close();
		}
		return todo; 
	}
	
	
	@Required
	public void setTodoSessionFactory(SessionFactory todoSessionFactory) {
		this.todoSessionFactory = todoSessionFactory;
	}
}
