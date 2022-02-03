package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.repository.INewsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class TemporalNewsService {
/*
COMO usuario administrador (ROLE: ADMIN)
QUIERO eliminar una actividad existente
PARA mantener la información actualizada

Criterios de aceptación:
DELETE /news/:id - Deberá validar la existencia en base al id enviado por parámetro. En el caso de
que exista, eliminarla (softDelete==true).
 */

  //////////////// //NewsController///////////////////////////////

 /* @Autowired
  private IDeleteNews deleteNews;

  @DeleteMapping("news/:id")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    deleteNews.delete(id);

    return ResponseEntity.noContent().build();
  } */


  //////////////////// interface IDeleteNews////////////////
  /* void delete(Long id);
   */
  /////////////NewsService/////////////////////
  @Autowired
  private INewsRepository newsRepository;

 /* @Override
  public void delete(Long id) {

    News news = newsRepository.findByNewsIdAndSoftDeleteFalse(id);

    if (news == null || news.isSoftDelete()) {
      throw new NotFoundException("News not found");
    }
    news.setSoftDelete(true);
    newsRepository.save(news);

  }*/

}


