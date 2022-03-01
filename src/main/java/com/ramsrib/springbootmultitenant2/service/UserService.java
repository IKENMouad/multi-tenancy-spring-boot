package com.ramsrib.springbootmultitenant2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramsrib.springbootmultitenant2.annotation.DisableTenantFilter;
import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	@DisableTenantFilter
	public List<User> listUsers() {
		List<User> users = userRepository.findAll();
		System.out.println("size : " + users.size());
		return users;
	}

	@DisableTenantFilter
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

	/*
	 * @Override public void run(ApplicationArguments applicationArguments) throws
	 * Exception { try { TenantContext.setCurrentTenant("tenant1"); if
	 * (userRepository.getByName("user1") == null) { userRepository.save(new
	 * User(null, "user1", null)); } if (userRepository.getByName("user2") == null)
	 * { TenantContext.setCurrentTenant("tenant2"); userRepository.save(new
	 * User(null, "user2", null)); } TenantContext.clear(); } catch (Exception e) {
	 * throw new RuntimeException("data not found"); } }
	 */
}
