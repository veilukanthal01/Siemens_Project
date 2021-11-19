package com.veilu.sprinboot.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Employee;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.exception.AssetAlredayExistsException;
import com.veilu.sprinboot.exception.AssetNotFoundException;
import com.veilu.sprinboot.exception.EmployeeNotFoundException;
import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.service.AssetsService;
import com.veilu.sprinboot.service.EmployeeService;
import com.veilu.sprinboot.service.OrganizationService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
	
	@Autowired
	private AssetsService assetsService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Assets> getAllAssets(){
		return this.assetsService.findAllAssets();
		
	}
	
	@PostMapping
	public ResponseEntity createAsset(@RequestBody Assets asset) {
		try {
			 return new ResponseEntity<Assets>(this.assetsService.saveAsset(asset), HttpStatus.OK);
		}
		catch (AssetAlredayExistsException ee){
			return new ResponseEntity<>(ee.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	//Map  assets to the particular Organization
	@PutMapping("/{assetId}/organization/{orgId}") public ResponseEntity assignAssetToOrganization(
			@PathVariable long assetId,
			@PathVariable long orgId
			) {
		try {
		Assets asset = assetsService.findById(assetId);
		Organization org = organizationService.findById(orgId);
		asset.setOrganization(org);
		return new ResponseEntity<Assets>(this.assetsService.mapAsset(asset), HttpStatus.OK);
		}
		catch(AssetNotFoundException aex) {
			return new ResponseEntity<>(aex.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (OrganizationNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{assetId}")
	public ResponseEntity getAssetById(@PathVariable long assetId){
		try {
			 return new ResponseEntity<Assets>(this.assetsService.findById(assetId), HttpStatus.OK);
		}
		catch (AssetNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/{assetId}")
	public String deleteAssetById(@PathVariable long assetId){
		this.assetsService.deleteAssetById(assetId);
		return"success";
		
	}
	
	//Assign assets to employees
		@PutMapping("/{assetId}/employee/{empId}") public ResponseEntity assignAssetToEmployee(
				@PathVariable long assetId,
				@PathVariable long empId
				) {
			try {
			Assets asset = assetsService.findById(assetId);
			Employee emp = employeeService.findEmployeeById(empId);
			asset.setEmployee(emp);
			return new ResponseEntity<Assets>(this.assetsService.mapAsset(asset), HttpStatus.OK);
			}
			catch(AssetNotFoundException aex) {
				return new ResponseEntity<>(aex.getMessage(), HttpStatus.NOT_FOUND);
			}
			catch (EmployeeNotFoundException ex) {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
			}
			
		}

}
