package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListMembersResponse extends PaginationResponse {

  @JsonProperty("members")
  private List<MemberResponse> memberResponses;
}