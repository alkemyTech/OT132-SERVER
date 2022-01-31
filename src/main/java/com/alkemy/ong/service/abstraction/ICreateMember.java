package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.response.MemberResponse;

public interface ICreateMember {

  MemberResponse save(CreateMemberRequest createMemberRequest);
}
