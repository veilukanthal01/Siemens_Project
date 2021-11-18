package com.veilu.sprinboot.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "organization", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "org_name") })
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	private long id;
	
	@Column(name = "org_name", unique = true, nullable = false, length = 100)
	private String name;
	
	@Column(unique = false, nullable = false, length = 100)
	private String country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
	Set<Assets> assets = new HashSet<Assets>();
	
	public Organization() {
		
	}
	
	public Organization(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Assets> getAssets() {
		return assets;
	}

	public void setAssets(Set<Assets> assets) {
		this.assets = assets;
	}
	
}
