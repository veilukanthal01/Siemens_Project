package com.veilu.sprinboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.service.AssetsService;
import com.veilu.sprinboot.service.OrganizationService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
	
	@Autowired
	private AssetsService assetsService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@GetMapping
	public List<Assets> getAllAssets(){
		return this.assetsService.findAllAssets();
		
	}
	
	@PostMapping
	public Assets createAsset(@RequestBody Assets asset) {
		return this.assetsService.saveAsset(asset);
	}
	
	@PutMapping("/{subjectId}/organization/{orgId}") public Assets assignAssetToOrganization(
			@PathVariable long subjectId,
			@PathVariable long orgId
			) {
		Assets asset = assetsService.findById(subjectId);
		Organization org = organizationService.findById(orgId);
		asset.setOrganization(org);
		return this.assetsService.saveAsset(asset);
	}
	
	@GetMapping("/{assetId}")
	public Assets getAssetById(@PathVariable long assetId){
		return this.assetsService.findById(assetId);
		
	}
	
	@DeleteMapping("/{assetId}")
	public String deleteAssetById(@PathVariable long assetId){
		Assets asset = assetsService.findById(assetId);
		this.assetsService.deleteAssetById(assetId);
		return"success";
		
	}

}
