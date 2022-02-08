package com.alkemy.ong.mapper;


import org.springframework.stereotype.Component;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.OrganizationResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class OrganizacionSocialMapper {

    @Autowired
    private SlideMapper slideMapper;

    public OrganizationResponse map(Organization organization, List<Slide> slides) {
        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setInstagramUrl(organization.getInstagramUrl());
        organizationResponse.setFacebookUrl(organization.getFacebookUrl());
        organizationResponse.setLinkedinUrl(organization.getLinkedinUrl());
        organizationResponse.setSlides(slideMapper.map(slides));
        return organizationResponse;
    }

}
