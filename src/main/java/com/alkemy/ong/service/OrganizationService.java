package com.alkemy.ong.service;

import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements IGetOrganizationDetails{
	
	@Autowired
	private IOrganizationRepository organizationRepository;
	
	@Autowired
	private OrganizationMapper mapper;


	@Override
	public OrganizationResponse find(){
		Organization organization = organizationRepository.findAll().get(0);
		OrganizationResponse organizationResponse = mapper.map(organization);
		return organizationResponse;
	}

}
