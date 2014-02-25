package service;


import model.Address;
import model.User;
import model.dto.SubscriptionDTO;

import org.springframework.beans.factory.annotation.Required;

import dao.UserDao;


public class SubscriptionService {

	private UserDao userDao;
	
	public void storeUser(SubscriptionDTO subscriptionDTO ){
		
		User user = new User();
		user.setEmail(subscriptionDTO.getEmail());
		user.setAddress(null);
		user.setFirstName(subscriptionDTO.getFirstName());
		user.setLastName(subscriptionDTO.getLastName());
		user.setPassword(subscriptionDTO.getPassword());
		
		Address address = new Address();
		address.setNumber(subscriptionDTO.getNumber());
		address.setStreet(subscriptionDTO.getStreet());
		address.setTown(subscriptionDTO.getTown());
		address.setCountry(subscriptionDTO.getCountry());
		user.setAddress(address);
		
		userDao.store(user);
	}
	
	@Required
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
