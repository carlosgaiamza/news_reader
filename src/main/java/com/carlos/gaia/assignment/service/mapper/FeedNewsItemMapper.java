package com.carlos.gaia.assignment.service.mapper;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FeedNewsItemMapper {

    public NewsItem map(SyndEntry syndEntry) {
        NewsItem item = new NewsItem();

        item.setTitle(syndEntry.getTitle());
        if (!Objects.isNull(syndEntry.getDescription())) {
            item.setDescription(syndEntry.getDescription().getValue());
        }
        item.setImage(syndEntry.getLink());
        item.setPublicationDate(syndEntry.getPublishedDate());
        item.setUpdatedDate(syndEntry.getUpdatedDate());

        return item;
    }

    public List<NewsItem> map(List<SyndEntry> entries) {
        var newsItems = new ArrayList<NewsItem>();
        entries.forEach(item -> newsItems.add(map(item)));
        return newsItems;
    }
}
