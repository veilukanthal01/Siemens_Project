package com.veilu.sprinboot.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veilu.sprinboot.entity.Assets;

@Repository
public interface AssetRepository  extends CrudRepository<Assets, Long>{
	
	@Query(value = "select a.serialNumber from Assets a")
	Set<String> getAssetBySerialNumber();
}


