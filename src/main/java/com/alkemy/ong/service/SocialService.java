package com.alkemy.ong.service;


import com.alkemy.ong.mapper.OrganizacionSocialMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SocialService implements IGetOrganizationDetails {

    @Autowired
    private OrganizacionSocialMapper mapper;

    @Autowired
    private ISlideRepository slideRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse find() {
        Organization organization = organizationRepository.findAll().get(0);
        List<Slide> slides = slideRepository.findAll(Sort.by("order"));
        return mapper.map(organization, slides);
    }
}