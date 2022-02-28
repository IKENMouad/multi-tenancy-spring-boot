package com.ramsrib.springbootmultitenant2.filter;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

@Component
public class MyInterceptor extends EmptyInterceptor {
 	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("onSave: " + propertyNames[0] + " -  " + state[0] + " id " + id);
		if (entity instanceof User) {
			((User) entity).setTenantId(TenantContext.getCurrentTenant());
		}
		return false;
	}
 
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof User) {
			((User) entity).setTenantId(TenantContext.getCurrentTenant());
		}
	}
 
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println("onFlushDirty: " + entity.getClass() + "id " + id);
		if (entity instanceof User) {
			((User) entity).setTenantId(TenantContext.getCurrentTenant());
		}
		return false;
	}
}