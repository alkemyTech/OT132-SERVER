
package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

	public OrganizationDTO entityToDTO(Organization organization){
		OrganizationDTO dto = new OrganizationDTO();
		dto.setName(organization.getName());
		dto.setImage(organization.getImage());
		dto.setAddress(organization.getAddress());
		dto.setPhone(organization.getPhone());
		return dto;		
	}

}
