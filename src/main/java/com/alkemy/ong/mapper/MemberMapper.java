package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.response.MemberResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

  public MemberResponse map(Member member) {
    MemberResponse memberResponse = new MemberResponse();
    memberResponse.setName(member.getName());
    memberResponse.setFacebookUrl(member.getFacebookUrl());
    memberResponse.setInstagramUrl(member.getInstagramUrl());
    memberResponse.setLinkedinUrl(member.getLinkedinUrl());
    memberResponse.setImage(member.getImage());
    memberResponse.setDescription(member.getDescription());
    return memberResponse;
  }

  public List<MemberResponse> map(List<Member> members) {
    List<MemberResponse> memberResponses = new ArrayList<>(members.size());
    for (Member member : members) {
      memberResponses.add(map(member));
    }
    return memberResponses;
  }
}
