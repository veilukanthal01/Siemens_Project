package com.veilu.sprinboot.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.veilu.sprinboot.entity.Assets;
import com.veilu.sprinboot.entity.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

	@Query(value = "select org.assets from Organization org where org.id = ?1")
	Set<Assets> getAssetsbyOrganization(long id);
	
	@Query(value = "select org.name from Organization org")
	Set<String> getOrganizationByName();
}
