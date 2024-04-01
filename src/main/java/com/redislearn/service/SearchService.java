package com.redislearn.service;

import com.redislearn.dto.PostDto;
import com.redislearn.entity.Posts;
import com.redislearn.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

  private final PostRepository postRepository;
  private final RedisTemplate<String, String> redisTemplate;

  public List<PostDto> searchTitle(String title) {
    List<Posts> results = postRepository.findByTitleLike(title);
    List<PostDto> response = new ArrayList<>();
    for (Posts result : results) {
      response.add(PostDto.builder()
          .title(result.getTitle())
          .build());
    }

    try {
      redisTemplate.opsForZSet().incrementScore("ranking", title, 1);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    return response;
  }

  public List<String> getSearchRankList() {
    String key = "ranking";
    ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
    Set<TypedTuple<String>> typedTuples = zSetOperations.reverseRangeByScoreWithScores(key, 0, 9);
    if (typedTuples != null) {
      List<String> rankList = new ArrayList<>();
      for (TypedTuple<String> typedTuple : typedTuples) {
        rankList.add(typedTuple.getValue());
      }

      return rankList;
    } else {

      return null;
    }
  }
}
