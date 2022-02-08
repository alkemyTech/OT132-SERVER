package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.SlideResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

  @Autowired
  private SlideMapper slideMapper;

  public OrganizationResponse map(Organization organization, List<Slide> slides) {
    OrganizationResponse organizationResponse = map(organization);
    organizationResponse.setSlides(map(slides));
    return organizationResponse;
  }

  private List<SlideResponse> map(List<Slide> slides) {
    return slideMapper.map(slides,
        SlideAttributes.TEXT,
        SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER);
  }

  public OrganizationResponse map(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    organizationResponse.setName(organization.getName());
    organizationResponse.setImage(organization.getImage());
    organizationResponse.setEmail(organization.getEmail());
    organizationResponse.setWelcomeText(organization.getWelcomeText());
    organizationResponse.setAddress(organization.getAddress());
    organizationResponse.setPhone(organization.getPhone());
    organizationResponse.setAboutUsText(organization.getAboutUsText());
    organizationResponse.setFacebookUrl(organization.getFacebookUrl());
    organizationResponse.setInstagramUrl(organization.getInstagramUrl());
    organizationResponse.setLinkedinUrl(organization.getLinkedinUrl());
    return organizationResponse;
  }

}
