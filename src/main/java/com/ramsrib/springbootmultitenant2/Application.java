package com.ramsrib.springbootmultitenant2;

import java.util.Map;

import com.ramsrib.springbootmultitenant2.filter.MyInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

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