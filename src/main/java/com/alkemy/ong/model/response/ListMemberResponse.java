package com.alkemy.ong.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListMemberResponse {

    private Long memberId;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
}
