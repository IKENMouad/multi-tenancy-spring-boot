package com.ramsrib.springbootmultitenant2;

import java.io.Serializable;
import java.util.Map;

import com.ramsrib.springbootmultitenant2.model.TenantSupport;
import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
class MyInterceptor extends EmptyInterceptor {

  @Override
  public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
    System.out.println("onSave: " + entity.getClass());
    if (entity instanceof User) {
      ((User) entity).setTenantId(TenantContext.getCurrentTenant());
    }
    return false;
  }

  @Override
  public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
    if (entity instanceof TenantSupport) {
      ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
    }
  }

  @Override
  public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
      String[] propertyNames, Type[] types) {
    if (entity instanceof TenantSupport) {
      ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
    }
    return false;
  }

}

@Component
class MyInterceptorRegistration implements HibernatePropertiesCustomizer {

  @Autowired
  private MyInterceptor myInterceptor;

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put("hibernate.ejb.interceptor", myInterceptor);
  }
}