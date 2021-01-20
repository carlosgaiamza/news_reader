package com.carlos.gaia.assignment.graphQL;


import com.carlos.gaia.assignment.graphQL.domain.NewsItem;
import com.carlos.gaia.assignment.graphQL.mapper.DomainToGraphQLItemMapper;
import com.carlos.gaia.assignment.service.repository.NewsItemRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private NewsItemRepository repository;
    @Autowired
    private DomainToGraphQLItemMapper mapper;

    public List<NewsItem> recentItems(){
        return mapper.map((List<com.carlos.gaia.assignment.service.domain.NewsItem>) Optional.ofNullable(repository.findAll()).orElseGet(Collections::emptyList));
    }

}
