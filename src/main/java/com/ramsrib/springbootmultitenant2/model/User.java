package com.ramsrib.springbootmultitenant2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import java.util.List;

@Data
@Entity
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FilterDef(name = "tenantFilter", parameters = { @ParamDef(name = "tenantId", type = "string") })
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class User implements TenantSupport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_unities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "unite_id"))
  private List<Unite> unites;;

  private String tenantId;

}
