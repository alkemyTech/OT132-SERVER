package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateOrganizationRequest;
import com.alkemy.ong.model.response.OrganizationResponse;

public interface IUpdateOrganization {

  OrganizationResponse update(UpdateOrganizationRequest updateOrganizationRequest);

}
