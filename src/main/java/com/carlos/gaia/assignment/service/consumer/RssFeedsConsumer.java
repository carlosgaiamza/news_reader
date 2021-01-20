package com.carlos.gaia.assignment.service.consumer;

import com.carlos.gaia.assignment.service.converter.FeedNewsItemMapper;
import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.carlos.gaia.assignment.service.repository.NewsItemRepository;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class RssFeedsConsumer {

    @Autowired
    private FeedNewsItemMapper converter;
    @Autowired
    private NewsItemRepository repository;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void retrievingFeedsTask() throws Exception {

        URL feedSource = new URL("http://rssblog.whatisrss.com/feed/");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));

        List<NewsItem> newsItems = (List<NewsItem>) converter.map(feed.getEntries());
        repository.saveAll(newsItems);
    }

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void showingFeedsTask() throws Exception {

        URL feedSource = new URL("http://rssblog.whatisrss.com/feed/");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));

        var newsItems = repository.findAll();
        System.out.println("#############################");
        newsItems.forEach(newsItem -> System.out.println(newsItem.getTitle()));
        System.out.println("#############################");
    }

}
