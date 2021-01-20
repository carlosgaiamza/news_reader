package com.carlos.gaia.assignment.graphQL.mapper;

import com.carlos.gaia.assignment.graphQL.domain.NewsItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DomainToGraphQLItemMapper {

    public List<NewsItem> map(List<com.carlos.gaia.assignment.service.domain.NewsItem> items) {
        var result = new ArrayList<NewsItem>();
        items.forEach(item -> result.add(map(item)));
        return result;
    }

    public NewsItem map(com.carlos.gaia.assignment.service.domain.NewsItem item) {
        NewsItem result = new NewsItem();
            result.setTitle(item.getTitle());
            result.setDescription(item.getDescription());
            result.setPublicationDate(item.getPublicationDate());
            result.setImage(item.getImage());
        return result;
    }
}
