package com.veilu.sprinboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.repository.AssetRepository;

@Service
public class AssetsService {
	@Autowired
	private AssetRepository assetRepository;
	
	public List<Assets> findAllAssets() {
		return (List)this.assetRepository.findAll();
		
	}
	
	public Assets saveAsset(Assets asset) {
		return this.assetRepository.save(asset);
		
	}
	
	public Assets findById(long assetId) {
		return this.assetRepository.findById(assetId).get();
		
	}
	
	public void deleteAssetById(long assetId) {
		 this.assetRepository.deleteById(assetId);
		
	}
}
