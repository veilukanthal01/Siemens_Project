package com.veilu.sprinboot.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.exception.AssetAlredayExistsException;
import com.veilu.sprinboot.exception.AssetNotFoundException;
import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.repository.AssetRepository;

@Service
public class AssetsService {
	@Autowired
	private AssetRepository assetRepository;
	
	public List<Assets> findAllAssets() {
		return (List)this.assetRepository.findAll();
		
	}
	
	public Assets saveAsset(Assets asset) throws AssetAlredayExistsException {
		Set<String> assetList = this.assetRepository.getAssetBySerialNumber();
		if(assetList.stream().anyMatch(a-> a.equals(asset.getSerialNumber()))) {
			 throw new AssetAlredayExistsException("Asset  alreday exists with the Serial Number: " + asset.getSerialNumber());
		}
		return this.assetRepository.save(asset);
		
	}
	public Assets mapAsset(Assets asset){
		return this.assetRepository.save(asset);
		
	}
	
	public Assets findById(long assetId) throws AssetNotFoundException {
		
		if(this.assetRepository.findById(assetId).isEmpty()) {
			throw new AssetNotFoundException("Asset not found with the Id : "+ assetId);
		} else {
			return this.assetRepository.findById(assetId).get();
		}
		
	}
	
	public Assets findAssetsById(long assetId) {
		 return this.assetRepository.findById(assetId).get();
		
	}
	
	public void deleteAssetById(long assetId) {
		 this.assetRepository.deleteById(assetId);
		
	}
}
