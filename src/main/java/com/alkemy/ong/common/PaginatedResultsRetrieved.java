package com.alkemy.ong.common;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaginatedResultsRetrieved {

  public void addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder,
      final HttpServletResponse response, String path,final int page, final int totalPages,
      final int pageSize) {
    uriBuilder.path(path);

    final StringBuilder linkHeader = new StringBuilder();
    if (hasNextPage(page, totalPages)) {
      final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
      linkHeader.append(createLinkHeader(uriForNextPage, "next"));
    }
    if (hasPreviousPage(page)) {
      final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriForPrevPage, "prev"));
    }

    response.addHeader("Link", linkHeader.toString());
  }

  String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page,
      final int size) {
    return uriBuilder.replaceQueryParam("page", page + 1).replaceQueryParam("size", size).build()
        .encode().toUriString();
  }

  String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page,
      final int size) {
    return uriBuilder.replaceQueryParam("page", page - 1).replaceQueryParam("size", size).build()
        .encode().toUriString();
  }

  boolean hasNextPage(final int page, final int totalPages) {
    return page < totalPages - 1;
  }

  boolean hasPreviousPage(final int page) {
    return page > 0;
  }

  void appendCommaIfNecessary(final StringBuilder linkHeader) {
    if (linkHeader.length() > 0) {
      linkHeader.append(", ");
    }
  }

  public static String createLinkHeader(final String uri, final String rel) {
    return "<" + uri + ">; rel=\"" + rel + "\"";
  }
}
