package com.alkemy.ong.service;

import com.alkemy.ong.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired private CategoryMapper mapper;
}
