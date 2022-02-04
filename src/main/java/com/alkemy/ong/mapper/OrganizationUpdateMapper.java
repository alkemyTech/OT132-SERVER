package com.alkemy.ong.mapper;

import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationUpdateResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUpdateMapper {

  public OrganizationUpdateResponse map(OrganizationRequest organizationRequest) {
    OrganizationUpdateResponse updateResponse = new OrganizationUpdateResponse();
    updateResponse.setName(organizationRequest.getName());
    updateResponse.setImage(organizationRequest.getImage());
    updateResponse.setEmail(organizationRequest.getEmail());
    updateResponse.setWelcomeText(organizationRequest.getWelcomeText());
    updateResponse.setAddress(organizationRequest.getAddress());
    updateResponse.setPhone(organizationRequest.getPhone());
    updateResponse.setAboutUsText(organizationRequest.getAboutUsText());
    updateResponse.setFacebookUrl(organizationRequest.getFacebookUrl());
    updateResponse.setInstagramUrl(organizationRequest.getInstagramUrl());
    updateResponse.setLinkedinUrl(organizationRequest.getLinkedinUrl());
    return updateResponse;
  }

}
