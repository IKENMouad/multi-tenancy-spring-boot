package com.ramsrib.springbootmultitenant2.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class EntityManagerConfig {

	@PersistenceContext
	public EntityManager entityManager;

	public EntityManagerConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
