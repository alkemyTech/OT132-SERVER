package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

  public OrganizationResponse map(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    organizationResponse.setName(organization.getName());
    organizationResponse.setImage(organization.getImage());
    organizationResponse.setAddress(organization.getAddress());
    organizationResponse.setPhone(organization.getPhone());
    return organizationResponse;
  }

}
