package com.carlos.gaia.assignment.service.converter;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedNewsItemMapper {

    public NewsItem map(SyndEntry syndEntry) {
        NewsItem item = new NewsItem();

        item.setTitle(syndEntry.getTitle());
        item.setDescription(syndEntry.getDescription().getValue());
        item.setImage(syndEntry.getLink());
        item.setPublicationDate(syndEntry.getPublishedDate());

        return item;
    }

    public List<NewsItem> map(List<SyndEntry> entries) {
        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        entries.forEach(item -> {
            newsItems.add(map((SyndEntry) item));
        });
        return newsItems;
    }
}
