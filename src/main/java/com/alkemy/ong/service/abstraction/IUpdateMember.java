package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateMemberRequest;

public interface IUpdateMember {

  void update(Long id, UpdateMemberRequest updateMemberRequest);

}
