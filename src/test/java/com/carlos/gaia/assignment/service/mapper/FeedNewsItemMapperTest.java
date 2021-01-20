package com.carlos.gaia.assignment.service.mapper;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedNewsItemMapperTest {

    private final FeedNewsItemMapper mapper = new FeedNewsItemMapper();

    @Test
    void mapTest() {

        List<SyndEntry> entries = new ArrayList<>();

        SyndEntry syndEntry1 = mock(SyndEntry.class);
        when(syndEntry1.getTitle()).thenReturn("Mocked title 1.");
        entries.add(syndEntry1);

        SyndEntry syndEntry2 = mock(SyndEntry.class);
        when(syndEntry2.getTitle()).thenReturn("Mocked title 2.");
        SyndContent description = mock(SyndContent.class);
        when(description.getValue()).thenReturn("Mocked description 2.");
        when(syndEntry2.getDescription()).thenReturn(description);
        entries.add(syndEntry2);

        List<NewsItem> result = mapper.map(entries);

        assertNotNull(result,"Result should not be null.");

        assertEquals(2, result.size(),"Result list should should have one and only value.");
        assertEquals("Mocked title 1.", result.get(0).getTitle(),"Wrong result title.");
        assertNull(result.get(0).getDescription(),"Wrong description 1.");

        assertNotNull(result.get(1).getDescription(),"Wrong result title.");
        assertEquals("Mocked title 2.", result.get(1).getTitle(),"Wrong result title.");
        assertEquals("Mocked description 2.", result.get(1).getDescription(),"Wrong description 2.");

        verify(syndEntry1).getTitle();
        verify(syndEntry1).getDescription();
        verify(syndEntry1).getLink();
        verify(syndEntry1).getPublishedDate();
        verify(syndEntry1).getUpdatedDate();

        verify(syndEntry2).getTitle();
        verify(syndEntry2, times(2)).getDescription();
        verify(syndEntry2).getLink();
        verify(syndEntry2).getPublishedDate();
        verify(syndEntry2).getUpdatedDate();

        verify(description).getValue();

        verifyNoMoreInteractions(syndEntry1, syndEntry2, description);
    }

    @Test
    void mapTestEmptyFeed() {

        List<SyndEntry> entries = new ArrayList<>();
        List<NewsItem> result = mapper.map(entries);
        assertNotNull(result,"Result should not be null.");
        assertEquals(0, result.size(),"Result list should should have one and only value.");
    }
}