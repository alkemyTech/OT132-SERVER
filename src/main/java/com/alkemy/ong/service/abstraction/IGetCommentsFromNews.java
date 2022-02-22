package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListCommentsInNewsResponse;

public interface IGetCommentsFromNews {

  ListCommentsInNewsResponse list(Long id);
}
