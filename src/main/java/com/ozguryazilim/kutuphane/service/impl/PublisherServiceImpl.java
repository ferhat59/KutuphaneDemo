package com.ozguryazilim.kutuphane.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozguryazilim.kutuphane.model.Dto.PublisherDto;
import com.ozguryazilim.kutuphane.model.Entity.Publisher;
import com.ozguryazilim.kutuphane.repository.PublisherRepo;
import com.ozguryazilim.kutuphane.service.PublisherService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepo repo;

    @Override
    public Publisher savePublisher(String publisherName,String description) throws Exception {
        Publisher publisher = new Publisher();
        Publisher entity = repo.findByPublisherName(publisherName);
        if (entity != null){
            throw new Exception("Aynı yayınevinden var");
        }
        publisher.setPublisherName(publisherName);
        publisher.setDescription(description);
        return repo.save(publisher);
    }

    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publisherList = repo.findAll();
        List<PublisherDto> dtoList = new ArrayList<>();
        for (Publisher publisher : publisherList) {
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setId(publisher.getId());
            publisherDto.setPublisherName(publisher.getPublisherName());
            dtoList.add(publisherDto);
        }
        return dtoList;
    }

    @Override
    public Publisher findByName(String name) {
        return repo.findByPublisherName(name);
    }
}
