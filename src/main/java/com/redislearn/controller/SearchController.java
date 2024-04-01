package com.redislearn.controller;

import com.redislearn.dto.PostDto;
import com.redislearn.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {

  private final SearchService searchService;

  @GetMapping("/redis-learn/search")
  public List<PostDto> search(String title) {
    return searchService.searchTitle(title);
  }

  @GetMapping("/redis-learn/search-rank")
  public List<String> getSearchRank() {
    return searchService.getSearchRankList();
  }
}
