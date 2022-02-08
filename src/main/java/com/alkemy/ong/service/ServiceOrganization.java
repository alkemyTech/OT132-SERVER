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
    Organization organization = findOrganization();
    List<Slide> slides = slideRepository.findAll(Sort.by(SlideAttributes.ORDER.getFieldName()));
    return mapper.map(organization, slides);
  }


  @Override
  public OrganizationResponse update(UpdateOrganizationRequest updateOrganizationRequest) {
    Organization organization = findOrganization();
    mapper.map(updateOrganizationRequest, organization);
    return mapper.map(organizationRepository.save(organization));
  }

  public Organization findOrganization() {
    return organizationRepository.findAll().get(0);
  }

}
