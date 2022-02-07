package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.request.OrganizationUpdateRequest;

public interface IOrganizationUpdate {

  Organization update(OrganizationUpdateRequest organizationUpdateRequest);

}
