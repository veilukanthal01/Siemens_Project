package com.veilu.sprinboot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "org_name")
	private String name;
	
	@Column
	private String country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
	List<Assets> assets = new ArrayList<Assets>();
	
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
	public List<Assets> getAssets() {
		return assets;
	}
	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}
	
}
