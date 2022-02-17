package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateMemberRequest;
import com.alkemy.ong.model.request.UpdateMemberRequest;
import com.alkemy.ong.model.response.ListMembersResponse;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.service.abstraction.ICreateMember;
import com.alkemy.ong.service.abstraction.IDeleteMember;
import com.alkemy.ong.service.abstraction.IGetMemberDetails;
import com.alkemy.ong.service.abstraction.IUpdateMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/members")
@Api(tags = "Member Endpoints", value = "MemberEndpoints")
public class MemberController {

  private static final String MEMBER_PATH = "/members";

  @Autowired
  private IGetMemberDetails getMemberDetails;

  @Autowired
  private ICreateMember createMember;

  @Autowired
  private IUpdateMember updateMember;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @Autowired
  private IDeleteMember deleteMember;

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Return the list of members by severous pages")
  @ApiResponses(value = {
      @ApiResponse(code = 200,
          message = "OK - The list of members. The size of the page is the one"
          + "passed in the parameters",
          response = ListMembersResponse.class,
          responseHeaders = {
            @ResponseHeader(name = "Link",
                description = "Link of the previous page and another for the next page",
                response = String.class)
          }),
      @ApiResponse(code = 403,message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)
  })

  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "page",
          value = "Page of the list",
          required = true,
          paramType = "query",
          dataTypeClass = String.class,
          example = "0"),
      @ApiImplicitParam(name = "size",
          value = "Size of the page",
          required = false,
          paramType = "query",
          dataTypeClass = String.class,
          example = "10"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")
  })
  public ResponseEntity<ListMembersResponse> list(Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    ListMembersResponse memberResponses = getMemberDetails.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder,
        response,
        MEMBER_PATH,
        memberResponses.getPage(),
        memberResponses.getTotalPages(),
        memberResponses.getSize());
    return ResponseEntity.ok().body(memberResponses);
  }

  @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a member and return it.")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "OK - The member was successfully created",
          response = MemberResponse.class),
      @ApiResponse(code = 403,message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)
  })
  @ApiImplicitParam(name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<MemberResponse> create(
      @Valid @RequestBody CreateMemberRequest createMemberRequest) {
    MemberResponse memberResponse = createMember.save(createMemberRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
  }

  @PutMapping(value = "/{id}",produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Update a member passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The member was successfully created"),
      @ApiResponse(code = 403,message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)
  })
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the member we want to update",
          required = true,
          allowEmptyValue = false,
          paramType = "path",
          dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")
  })
  public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody
      UpdateMemberRequest updateMemberRequest) {
    updateMember.update(id, updateMemberRequest);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping(value = "/{id}",produces = {"application/json"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete a member passed by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "NO_CONTENT - The member was successfully deleted"),
      @ApiResponse(code = 403,message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)
  })
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "id",
          value = "Id of the member we want to delete",
          required = true,
          allowEmptyValue = false,
          paramType = "path",
          dataTypeClass = String.class,
          example = "1"),
      @ApiImplicitParam(name = "Authorization",
          value = "Access Token",
          required = true,
          allowEmptyValue = false,
          paramType = "header",
          dataTypeClass = String.class,
          example = "Bearer access_token")
  })
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteMember.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
