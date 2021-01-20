package com.carlos.gaia.assignment.service.consumer;

import com.carlos.gaia.assignment.service.mapper.FeedNewsItemMapper;
import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.carlos.gaia.assignment.service.servcice.ItemService;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class RssFeedsConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(RssFeedsConsumer.class);

    @Value("${rssFeedsConsumer.url}")
    private String url;

    @Autowired
    private FeedNewsItemMapper converter;

    @Autowired
    private ItemService service;

    @Scheduled(fixedRateString = "${rssFeedsConsumer.fixedRate.in.milliseconds}")
    public void retrievingFeedsTask() throws Exception {

        URL feedSource = new URL(url);

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));

        List<NewsItem> newsItems = converter.map(feed.getEntries());

        service.updateItems(newsItems);

        showFeeds();
    }

    private void showFeeds() {
        var newsItems = service.findAll();
        LOGGER.info("---> Starting displaying stored items:" );
        newsItems.forEach(newsItem -> LOGGER.info(newsItem.getTitle()));
        LOGGER.info("---> End displaying stored items." );
    }

}
