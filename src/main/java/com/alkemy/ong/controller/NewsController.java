package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateNewsRequest;
import com.alkemy.ong.model.request.UpdateNewsRequest;
import com.alkemy.ong.model.response.ListNewsResponse;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.service.abstraction.ICreateNews;
import com.alkemy.ong.service.abstraction.IDeleteNews;
import com.alkemy.ong.service.abstraction.IGetNewsDetails;
import com.alkemy.ong.service.abstraction.IUpdateNews;
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
@RequestMapping("news")
public class NewsController {

  private static final String NEWS_PATH = "/news";

  @Autowired
  private ICreateNews createNews;

  @Autowired
  private IDeleteNews deleteNews;

  @Autowired
  private IGetNewsDetails getNewsDetails;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @Autowired
  private IUpdateNews updateNews;

  @GetMapping
  public ResponseEntity<ListNewsResponse> list(Pageable pageable, UriComponentsBuilder uriBuilder,
      HttpServletResponse response) {
    ListNewsResponse listNewsResponse = getNewsDetails.findAll(pageable);

    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder,
        response, NEWS_PATH,
        listNewsResponse.getPage(),
        listNewsResponse.getTotalPages(),
        listNewsResponse.getSize());

    return ResponseEntity.ok(listNewsResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {

    deleteNews.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<NewsResponse> create(
      @RequestBody @Valid CreateNewsRequest createNewsRequest) {

    return ResponseEntity.status(HttpStatus.CREATED).body(createNews.create(createNewsRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<NewsResponse> update(@PathVariable(value = "id") Long id,
      @RequestBody @Valid UpdateNewsRequest updateNewsRequest) throws NotFoundException {
    NewsResponse newsResponse = updateNews.update(id, updateNewsRequest);
    return ResponseEntity.status(HttpStatus.OK).body(newsResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NewsResponse> getBy(@PathVariable(value = "id") Long id)
      throws NotFoundException {
    return ResponseEntity.ok().body(getNewsDetails.getBy(id));
  }

}
