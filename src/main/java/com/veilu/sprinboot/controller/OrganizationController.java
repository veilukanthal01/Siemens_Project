package com.veilu.sprinboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.service.OrganizationService;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@GetMapping
	public List<Organization> getAllOrganizations(){
		return this.organizationService.findAllOrganization();
		
	}
	
	@PostMapping
	public Organization createUser(@RequestBody Organization organization) {
		return this.organizationService.saveOrganization(organization);
	}
	
	@GetMapping("/{orgId}")
	public Organization getOrgById(@PathVariable long orgId){
		return this.organizationService.findById(orgId);
		
	}
	
	@GetMapping("/organization/{orgId}/assets")
	public List<Assets> getAssetsbyOrganization(@PathVariable long orgId){
		return this.organizationService.getAssetsbyOrganization(orgId);
		
	}
	
	@DeleteMapping("/{orgId}")
	public String  deleteOrgById(@PathVariable long orgId){
		Organization org = organizationService.findById(orgId);
		this.organizationService.deleteOrgById(orgId);
		return "success";
		
	}

}
