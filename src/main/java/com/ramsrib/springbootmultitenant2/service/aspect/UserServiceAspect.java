package com.ramsrib.springbootmultitenant2.service.aspect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ramsrib.springbootmultitenant2.annotation.DisableTenantFilter;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {

	@PersistenceContext
	private EntityManager entityManager;

	@Before("execution(* com.ramsrib.springbootmultitenant2.service.UserService.*(..))")
	public void aroundExecution(JoinPoint pjp) throws Throwable {
		// org.hibernate.Filter filter =
		// userService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
		org.hibernate.Filter filter = entityManager.unwrap(Session.class).enableFilter("tenantFilter");
		filter.setParameter("tenantId", TenantContext.getCurrentTenant());
		System.out.println("Before :");
		filter.validate();
	}

	@Before("@annotation(disableTenantFilter)")
	public void disableFilter(JoinPoint joinPoint, DisableTenantFilter disableTenantFilter) {
		System.out.println("Before the method");
		// enable of disable filter
		entityManager.unwrap(Session.class).disableFilter("tenantFilter");
	}

}
