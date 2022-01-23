
package com.alkemy.ong.service;

import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationDTO;
import com.alkemy.ong.repository.IOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
	
	@Autowired
	IOrganizationRepository repository;
	
	@Autowired
	OrganizationMapper mapper;
	
	public OrganizationDTO find(long id){
		Organization organization = repository.getById(id);
		OrganizationDTO dto = mapper.entityToDTO(organization);
		return dto;
	}
	
}
