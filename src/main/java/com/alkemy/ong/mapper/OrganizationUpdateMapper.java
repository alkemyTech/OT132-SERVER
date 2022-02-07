package com.alkemy.ong.mapper;

import com.alkemy.ong.model.request.OrganizationUpdateRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUpdateMapper {

  public OrganizationResponse map(OrganizationUpdateRequest organizationUpdateRequest) {
    OrganizationResponse updateResponse = new OrganizationResponse();
    updateResponse.setName(organizationUpdateRequest.getName());
    updateResponse.setImage(organizationUpdateRequest.getImage());
    updateResponse.setEmail(organizationUpdateRequest.getEmail());
    updateResponse.setWelcomeText(organizationUpdateRequest.getWelcomeText());
    updateResponse.setAddress(organizationUpdateRequest.getAddress());
    updateResponse.setPhone(organizationUpdateRequest.getPhone());
    updateResponse.setAboutUsText(organizationUpdateRequest.getAboutUsText());
    updateResponse.setFacebookUrl(organizationUpdateRequest.getFacebookUrl());
    updateResponse.setInstagramUrl(organizationUpdateRequest.getInstagramUrl());
    updateResponse.setLinkedinUrl(organizationUpdateRequest.getLinkedinUrl());
    return updateResponse;
  }

}
