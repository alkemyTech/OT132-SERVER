package com.alkemy.ong.service;

import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.repository.IMemberRepository;
import com.alkemy.ong.service.abstraction.ICreateMember;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IGetMemberDetails, ICreateMember {

  @Autowired
  private IMemberRepository memberRepository;

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public ListMembersResponse findAll(Pageable pageable) {
    Page<Member> page =
        memberRepository.findBySoftDeleteFalseOrderByTimestampDesc(pageable);
    List<MemberResponse> memberResponses = memberMapper.map(page.getContent());
    return buildListResponse(memberResponses, page);
  }

  @Override
  public MemberResponse save(CreateMemberRequest createMemberRequest) {
    Member member = memberMapper.map(createMemberRequest);
    member.setSoftDelete(false);
    return memberMapper.map(memberRepository.save(member));
  }

  private ListMembersResponse buildListResponse(List<MemberResponse> members,
      Page<Member> page) {
    ListMembersResponse listMembersResponse = new ListMembersResponse();
    listMembersResponse.setMemberResponses(members);
    listMembersResponse.setPage(page.getNumber());
    listMembersResponse.setSize(page.getSize());
    listMembersResponse.setTotalPages(page.getTotalPages());
    return listMembersResponse;
  }
}