package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.request.UpdateOrganizationRequest;

public interface IUpdateOrganization {

  Organization update(UpdateOrganizationRequest updateOrganizationRequest);

}
