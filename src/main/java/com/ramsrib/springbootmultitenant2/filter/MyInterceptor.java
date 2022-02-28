package com.ramsrib.springbootmultitenant2.filter;

import java.io.Serializable;

import com.ramsrib.springbootmultitenant2.model.TenantSupport;
import com.ramsrib.springbootmultitenant2.model.User;
import com.ramsrib.springbootmultitenant2.tenant.TenantContext;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

@Component
public class MyInterceptor extends EmptyInterceptor {

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
        System.out.println("onFlushDirty: " + entity.getClass() + "id " + id);
        if (entity instanceof TenantSupport) {
            ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
        }
        return false;
    }

}
