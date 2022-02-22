package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByOrderByTimestampAsc();

  List<Comment> findAllByNewsOrderByTimestampAsc(News news);
}
