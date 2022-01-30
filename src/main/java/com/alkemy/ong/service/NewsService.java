/*
COMO usuario administrador (ROLE: ADMIN)
QUIERO agregar una novedad en el sistema
PARA poder informar las Novedades de la ONG

Criterios de aceptación:
POST /news - Deberá validar la existencia de los campos enviados: text, image y name, para almacenar el registro en la tabla News. Antes de almacenarla, deberá asignarle la columna category con el valor "news".

Los mensajes de error deben estar en inglés.


Incidencias vinculadas


 */

package com.alkemy.ong.service;

import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.request.NewsRequest;
import com.alkemy.ong.model.response.NewsResponse;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IPostNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements IPostNews {
	
	@Autowired
	private INewsRepository newsRepository;

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public NewsResponse postNews(NewsRequest newsRequest) {
		
		
		NewsResponse newsResponse = newsMapper.responseMapper(newsRequest);
		News news = newsMapper.map(newsResponse);
		//Don't forget to add category to news.
		newsRepository.save(news);
		//Maybe it actually should be mapped differently from newsRequest to News, save and then to NewsResponse. also NewsResponse might need to travel with the ID .CHECK!!
		
		return new NewsResponse();
	}
}
