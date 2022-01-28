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
    OrganizationResponse organizationResponse = new OrganizationResponse();
    organizationResponse.setName(organization.getName());
    organizationResponse.setImage(organization.getImage());
    organizationResponse.setAddress(organization.getAddress());
    organizationResponse.setPhone(organization.getPhone());
    organizationResponse.setSlides(map(slides));
    return organizationResponse;
  }

  private List<SlideResponse> map(List<Slide> slides) {
    return slideMapper.mapList(slides,
        SlideAttributes.TEXT,
        SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER);
  }

}
