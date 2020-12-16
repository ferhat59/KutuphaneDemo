package com.ozguryazilim.kutuphane.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilim.kutuphane.model.Entity.Publisher;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Publisher findByPublisherName(String name);
}
