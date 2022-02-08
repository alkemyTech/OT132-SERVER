package com.alkemy.ong.service;

import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.ICreateMember;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements IGetMemberDetails, ICreateMember {

  @Autowired
  private IMemberRepository memberRepository;

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public ListMembersResponse findAll() {
    List<Member> members = memberRepository.findBySoftDeleteFalse();
    return buildListResponse(members);
  }

  @Override
  public MemberResponse save(CreateMemberRequest createMemberRequest) {
    Member member = memberMapper.map(createMemberRequest);
    member.setSoftDelete(false);
    return memberMapper.map(memberRepository.save(member));
  }

  private ListMembersResponse buildListResponse(List<Member> members) {
    List<MemberResponse> memberResponses = memberMapper.map(members);
    ListMembersResponse listMembersResponse = new ListMembersResponse();
    listMembersResponse.setMemberResponses(memberResponses);
    return listMembersResponse;
  }
}
