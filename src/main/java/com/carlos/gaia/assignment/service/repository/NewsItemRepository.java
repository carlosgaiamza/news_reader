package com.carlos.gaia.assignment.service.repository;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NewsItemRepository extends CrudRepository<NewsItem, String> {
}
