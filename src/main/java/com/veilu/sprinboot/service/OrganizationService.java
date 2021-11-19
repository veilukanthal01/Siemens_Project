package com.veilu.sprinboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	public List<Organization> findAllOrganization() {
		return (List) this.organizationRepository.findAll();

	}

	public Organization saveOrganization(Organization org) throws OrganizationAlredayExistsException {
		Set<String> orgList = this.organizationRepository.getOrganizationByName();
		if (orgList.stream().anyMatch(o -> o.equals(org.getName()))) {
			throw new OrganizationAlredayExistsException("Organization alreday exists with the name: " + org.getName());
		}
		return this.organizationRepository.save(org);
	}

	public Organization findById(long orgId) throws OrganizationNotFoundException {
		if (this.organizationRepository.findById(orgId).isEmpty()) {
			throw new OrganizationNotFoundException("Organization not found with the Id : " + orgId);
		} else {
			return this.organizationRepository.findById(orgId).get();
		}

	}

	public Organization findOrgById(long orgId) {
		return this.organizationRepository.findById(orgId).get();
	}

	public Set<Assets> getAssetsbyOrganization(long id) throws OrganizationNotFoundException {
		if (this.organizationRepository.findById(id).isEmpty()) {
			throw new OrganizationNotFoundException("Organization not found with the Id : " + id);
		} else {
			if (this.organizationRepository.getAssetsbyOrganization(id).isEmpty()) {
				return new HashSet<>();
			} else {
				return this.organizationRepository.getAssetsbyOrganization(id);
			}
		}
	}

	public void deleteOrgById(long orgId) {
		this.organizationRepository.deleteById(orgId);

	}

}
