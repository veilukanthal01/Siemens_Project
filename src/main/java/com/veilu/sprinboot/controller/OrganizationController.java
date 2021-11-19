package com.veilu.sprinboot.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.service.OrganizationService;
import com.veilu.sprinboot.toClasses.OrganizationAssetsTo;

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
	public ResponseEntity createUser(@RequestBody Organization organization) {
		try {
			 return new ResponseEntity<Organization>(this.organizationService.saveOrganization(organization), HttpStatus.OK);
		}
		catch (OrganizationAlredayExistsException ee){
			return new ResponseEntity<>(ee.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/{orgId}")
	public ResponseEntity getOrgById(@PathVariable long orgId){
		try {
			 return new ResponseEntity<Organization>(this.organizationService.findById(orgId), HttpStatus.OK);
		}
		catch (OrganizationNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	//find all assets for the particular Organization
	/*@GetMapping("/organization/{orgId}/assets")
	public Set<Assets> getAssetsbyOrganization(@PathVariable long orgId){
		return this.organizationService.getAssetsbyOrganization(orgId);
		
	}*/
	
	@GetMapping("/organization/{orgId}/assets")
	public ResponseEntity getAssetsbyOrganization(@PathVariable long orgId){
		 Set<Assets> assetsList =  this.organizationService.getAssetsbyOrganization(orgId);
		 Set<OrganizationAssetsTo> orgAssetsList = new HashSet<>();
		 if(!CollectionUtils.isEmpty(assetsList)) {
			 for(Assets asset: assetsList ) {
				 OrganizationAssetsTo oato = new OrganizationAssetsTo();
				 oato.setOrgId(asset.getOrganization().getId());
				 oato.setAssetId(asset.getId());
				 oato.setSerialNumber(asset.getSerialNumber());
				 oato.setOrgName(asset.getOrganization().getName());
				 orgAssetsList.add(oato);
			 }
			 return new ResponseEntity<>(orgAssetsList, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>("Organization does not have nay assets mapped", HttpStatus.NO_CONTENT);
		 }
	}
	
	@DeleteMapping("/{orgId}")
	public String  deleteOrgById(@PathVariable long orgId) {
		this.organizationService.deleteOrgById(orgId);
		return "success";
		
	}

}
