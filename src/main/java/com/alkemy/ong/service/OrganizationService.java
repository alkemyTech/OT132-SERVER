package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.OrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import com.alkemy.ong.service.abstraction.IOrganizationUpdate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements IGetOrganizationDetails, IOrganizationUpdate {

  @Autowired
  private IOrganizationRepository organizationRepository;

  @Autowired
  private ISlideRepository slideRepository;

  @Autowired
  private OrganizationMapper mapper;

  @Override
  public OrganizationResponse find() {
    Organization organization = organizationRepository.findAll().get(0);
    List<Slide> slides = slideRepository.findAll(Sort.by(SlideAttributes.ORDER.getFieldName()));
    return mapper.map(organization, slides);
  }


  @Override
  public void update(OrganizationRequest organizationRequest) throws NotFoundException {
    Organization organization = getOrganization();
    organization.setName(organizationRequest.getName());
    organization.setImage(organizationRequest.getImage());
    organization.setAddress(organizationRequest.getAddress());
    organization.setPhone(organizationRequest.getPhone());
    organization.setEmail(organizationRequest.getEmail());
    organization.setWelcomeText(organizationRequest.getWelcomeText());
    organization.setAboutUsText(organizationRequest.getAboutUsText());
    organization.setFacebookUrl(organizationRequest.getFacebookUrl());
    organization.setLinkedinUrl(organizationRequest.getLinkedinUrl());
    organization.setInstagramUrl(organizationRequest.getInstagramUrl());
    organizationRepository.save(organization);
  }

  private Organization getOrganization() throws NotFoundException {
    List<Organization> organizations = organizationRepository.findAll();
    if (organizations.isEmpty()) {
      throw new NotFoundException("The requested resource could not be found.");
    }
    return organizations.get(0);
  }

}
