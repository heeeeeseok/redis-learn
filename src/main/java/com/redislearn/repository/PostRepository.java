package com.redislearn.repository;

import com.redislearn.entity.Posts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {

  public List<Posts> findByTitleLike(String title);
}
