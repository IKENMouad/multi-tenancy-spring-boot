package com.ramsrib.springbootmultitenant2.service.aspect;

import com.ramsrib.springbootmultitenant2.service.UserService;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {
 
  // only applicable to user service
  // @Before("execution(* com.ramsrib.springbootmultitenant2.service.UserService.*(..)) && !execution(* com.ramsrib.springbootmultitenant2.service.UserService.run(..)) && target(userService)")
  // public void aroundExecution(JoinPoint pjp, UserService userService) throws Throwable {
  //   org.hibernate.Filter filter = userService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
  //   System.out.println( " in UserServiceAspect " + TenantContext.getCurrentTenant() );
  //    filter.setParameter("tenantId", TenantContext.getCurrentTenant());
  //   filter.validate();
  // }
}
 