package com.ramsrib.springbootmultitenant2;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import com.ramsrib.springbootmultitenant2.filter.MyInterceptor;
import com.ramsrib.springbootmultitenant2.service.UserService;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Component
class MyInterceptorRegistration implements HibernatePropertiesCustomizer {

	@Autowired
	private MyInterceptor myInterceptor;

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put("hibernate.session_factory.interceptor", myInterceptor);
	}
}