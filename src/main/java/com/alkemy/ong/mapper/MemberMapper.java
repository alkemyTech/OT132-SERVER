package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Member;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponse map(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(member.getMemberId());
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

    public Member map(CreateMemberRequest createMemberRequest) {
        Member member = new Member();
        member.setName(createMemberRequest.getName());
        member.setFacebookUrl(createMemberRequest.getFacebookUrl());
        member.setInstagramUrl(createMemberRequest.getInstagramUrl());
        member.setLinkedinUrl(createMemberRequest.getLinkedinUrl());
        member.setImage(createMemberRequest.getImage());
        member.setDescription(createMemberRequest.getDescription());
        return member;
    }
}
