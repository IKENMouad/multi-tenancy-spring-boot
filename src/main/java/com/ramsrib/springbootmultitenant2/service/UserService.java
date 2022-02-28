package com.ramsrib.springbootmultitenant2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.repository.UserRepository;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	public EntityManager entityManager;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public List<User> listUsers() {
		return userRepository.findAll();
	}

	public User getUser(long userId) {
		try {
			User user = userRepository.findById(userId).get();
			return user;
		} catch (Exception e) {
			System.out.println("error :" + e.getMessage() + " = " + e.getClass().getCanonicalName());
			return null;
		}
	}

	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		try {
			TenantContext.setCurrentTenant("tenant1");
			if (userRepository.getByName("user1") == null) {
				userRepository.save(new User(null, "user1", null));
			}
			if (userRepository.getByName("user2") == null) {
				TenantContext.setCurrentTenant("tenant2");
				userRepository.save(new User(null, "user2", null));
			}
			TenantContext.clear();
		} catch (Exception e) {
			throw new RuntimeException("data not found");
		}
	}
}
