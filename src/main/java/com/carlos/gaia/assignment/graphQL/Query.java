package com.carlos.gaia.assignment.graphQL;


import com.carlos.gaia.assignment.graphQL.domain.NewsItem;
import com.carlos.gaia.assignment.graphQL.mapper.DomainToGraphQLItemMapper;
import com.carlos.gaia.assignment.service.servcice.ItemService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(Query.class);

    @Autowired
    private ItemService service;
    @Autowired
    private DomainToGraphQLItemMapper mapper;

    public List<NewsItem> recentItems(){
        LOGGER.info("---> Query for items has been invoked.");
        return mapper.map(service.findAll());
    }

}
