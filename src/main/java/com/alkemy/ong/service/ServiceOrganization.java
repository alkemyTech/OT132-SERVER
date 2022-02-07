package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.UpdateOrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import com.alkemy.ong.service.abstraction.IUpdateOrganization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrganization implements IGetOrganizationDetails, IUpdateOrganization {

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
  public Organization update(UpdateOrganizationRequest updateOrganizationRequest)
      throws NotFoundException {
    Organization organization = organizationRepository.findAll().get(0);
    organization.setName(updateOrganizationRequest.getName());
    organization.setImage(updateOrganizationRequest.getImage());
    organization.setAddress(updateOrganizationRequest.getAddress());
    organization.setPhone(updateOrganizationRequest.getPhone());
    organization.setEmail(updateOrganizationRequest.getEmail());
    organization.setWelcomeText(updateOrganizationRequest.getWelcomeText());
    organization.setAboutUsText(updateOrganizationRequest.getAboutUsText());
    organization.setFacebookUrl(updateOrganizationRequest.getFacebookUrl());
    organization.setLinkedinUrl(updateOrganizationRequest.getLinkedinUrl());
    organization.setInstagramUrl(updateOrganizationRequest.getInstagramUrl());
    return organizationRepository.save(organization);
  }

}
