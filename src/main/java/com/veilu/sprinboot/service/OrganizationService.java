package com.veilu.sprinboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	public List<Organization> findAllOrganization() {
		return (List)this.organizationRepository.findAll();
		
	}
	
	public Organization saveOrganization(Organization org) {
		return this.organizationRepository.save(org);
		
	}
	public Organization findById(long orgId) {
		return this.organizationRepository.findById(orgId).get();
		
	}
	
	public List<Assets> getAssetsbyOrganization(long id) {
		return this.organizationRepository.getAssetsbyOrganization(id);
		
	}
	
	public void deleteOrgById(long orgId) {
		 this.organizationRepository.deleteById(orgId);
		
	}


}
