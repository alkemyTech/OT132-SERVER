/*
COMO usuario administrador (ROLE: ADMIN)
QUIERO agregar una novedad en el sistema
PARA poder informar las Novedades de la ONG

Criterios de aceptación:
POST /news - Deberá validar la existencia de los campos enviados: text, image y name,
para almacenar el registro en la tabla News. Antes de almacenarla, deberá asignarle la columna
category con el valor "news".

Los mensajes de error deben estar en inglés.


Incidencias vinculadas


 */

package com.alkemy.ong.service;

import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IAddCategory;
import com.alkemy.ong.service.abstraction.ICreateNews;
import com.alkemy.ong.service.abstraction.ISaveNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements ICreateNews, IAddCategory, ISaveNews {

  @Autowired private INewsRepository newsRepository;

  @Autowired private NewsMapper newsMapper;

  @Autowired private ICategoryRepository categoryRepository;

  @Override
  public NewsResponse create(NewsRequest newsRequest) {

    NewsResponse newsResponse = newsMapper.responseMapper(newsRequest);
    save(newsResponse);

    return new NewsResponse();
  }

  @Override
  public Category add() {

    Category category = categoryRepository.findByNameIgnoreCase("news");

    return category;
  }

  @Override
  public void save(NewsResponse newsResponse) {

    News news = newsMapper.map(newsResponse);

    news.setCategory(add());

    newsRepository.save(news);
  }
}
