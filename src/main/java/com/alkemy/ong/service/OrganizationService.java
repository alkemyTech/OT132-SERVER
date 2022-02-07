package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.OrganizationUpdateRequest;
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
  public Organization update(OrganizationUpdateRequest organizationUpdateRequest)
      throws NotFoundException {
    Organization organization = findOrganization();
    organization.setName(organizationUpdateRequest.getName());
    organization.setImage(organizationUpdateRequest.getImage());
    organization.setAddress(organizationUpdateRequest.getAddress());
    organization.setPhone(organizationUpdateRequest.getPhone());
    organization.setEmail(organizationUpdateRequest.getEmail());
    organization.setWelcomeText(organizationUpdateRequest.getWelcomeText());
    organization.setAboutUsText(organizationUpdateRequest.getAboutUsText());
    organization.setFacebookUrl(organizationUpdateRequest.getFacebookUrl());
    organization.setLinkedinUrl(organizationUpdateRequest.getLinkedinUrl());
    organization.setInstagramUrl(organizationUpdateRequest.getInstagramUrl());
    return organizationRepository.save(organization);
  }

  private Organization findOrganization() {
    List<Organization> organizations = organizationRepository.findAll();
    return organizations.get(0);
  }

}
