package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListMembersResponse;
import org.springframework.data.domain.Pageable;


public interface IGetMemberDetails {

  ListMembersResponse findAll(Pageable pageable);
}