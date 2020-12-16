package com.ozguryazilim.kutuphane.service;

import java.util.List;

import com.ozguryazilim.kutuphane.model.Dto.PublisherDto;
import com.ozguryazilim.kutuphane.model.Entity.Publisher;

public interface PublisherService {
    Publisher savePublisher(String publisherName,String description) throws Exception;
    List<PublisherDto> getAll();
    Publisher findByName(String name);
}
