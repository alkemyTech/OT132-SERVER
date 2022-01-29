package com.alkemy.ong.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListMembersResponse {

    @JsonProperty("members")
    private List<ListMembersResponse> memberResponses;
}
