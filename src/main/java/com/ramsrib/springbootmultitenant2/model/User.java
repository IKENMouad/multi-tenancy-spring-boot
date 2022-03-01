package com.ramsrib.springbootmultitenant2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Entity
@Table(name = "user_info") 
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(name = "tenantFilter", parameters = { @ParamDef(name = "tenantId", type = "string") })
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class User implements TenantSupport {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

 	private String tenantId;
 	
}
