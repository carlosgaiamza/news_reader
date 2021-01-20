package com.carlos.gaia.assignment.service.servcice;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import com.carlos.gaia.assignment.service.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository repository;

    public void updateItems(List<NewsItem> items) {
        var itemsToSave = new ArrayList<NewsItem>();

        items.forEach(item -> {
           var storedItem = repository.findByTitleAndPublicationDate(item.getTitle(), item.getPublicationDate());

           if(storedItem.isPresent()) {
               var existingItem = storedItem.get();

               if (!Objects.isNull(item.getUpdatedDate())
                       && !Objects.equals(item.getUpdatedDate(), existingItem.getUpdatedDate())) {

                   item.setId(existingItem.getId());
                   LOGGER.info("The \"{}\" item has been updated on \"{}\"", existingItem.getTitle(), existingItem.getUpdatedDate());
                   itemsToSave.add(item);
               }
           } else {
               LOGGER.info("The \"{}\" item has been added on \"{}\"", item.getTitle(), item.getPublicationDate());
               itemsToSave.add(item);
           }
        });
        repository.saveAll(itemsToSave);
    }

    public List<NewsItem> findAll() {
        return repository.findAllByOrderByPublicationDateAsc();
    }
}
