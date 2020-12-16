package com.ozguryazilim.kutuphane.service;

import java.util.List;


import com.ozguryazilim.kutuphane.model.Dto.AuthorDto;
import com.ozguryazilim.kutuphane.model.Entity.Author;
import com.ozguryazilim.kutuphane.model.Entity.Book;

public interface AuthorService {
    List<AuthorDto> getAll();
    Author findByAuthorName(String name);
    Author saveAuthor(String publisherName,String description) throws Exception;
    AuthorDto findByIdAndBookName(Long id, String bookName);
    List<Book> getBook(String name);
}
