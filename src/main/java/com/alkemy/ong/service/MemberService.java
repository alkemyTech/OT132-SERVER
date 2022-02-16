package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.request.UpdateMemberRequest;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.ICreateMember;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import com.alkemy.ong.service.abstraction.IUpdateMember;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IGetMemberDetails, ICreateMember, IUpdateMember {

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

  @Override
  public void update(Long id, UpdateMemberRequest updateMemberRequest) {
    Member member = findBy(id);
    updateValues(member, updateMemberRequest);
    memberRepository.save(member);
  }

  private Member findBy(Long id) {
    Optional<Member> result = memberRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException("Member not found");
    }
    return result.get();
  }

  private void updateValues(Member member, UpdateMemberRequest updateMemberRequest) {
    member.setName(updateMemberRequest.getName());
    member.setImage(updateMemberRequest.getImage());
    member.setDescription(updateMemberRequest.getDescription());
    member.setFacebookUrl(updateMemberRequest.getFacebookUrl());
    member.setInstagramUrl(updateMemberRequest.getInstagramUrl());
    member.setLinkedinUrl(updateMemberRequest.getLinkedinUrl());
  }

  private ListMembersResponse buildListResponse(List<Member> members) {
    List<MemberResponse> memberResponses = memberMapper.map(members);
    ListMembersResponse listMembersResponse = new ListMembersResponse();
    listMembersResponse.setMemberResponses(memberResponses);
    return listMembersResponse;
  }
}