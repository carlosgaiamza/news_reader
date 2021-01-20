package com.carlos.gaia.assignment.service.repository;

import com.carlos.gaia.assignment.service.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<NewsItem, String> {

    Optional<NewsItem> findByTitleAndPublicationDate(String title, Date publicationDate);

    List<NewsItem> findAllByOrderByPublicationDateAsc();

}
