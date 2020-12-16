package com.ozguryazilim.kutuphane.service;

import java.math.BigDecimal;
import java.util.List;

import com.ozguryazilim.kutuphane.Exception.AuthorAddException;

import com.ozguryazilim.kutuphane.model.Dto.BookDto;
import com.ozguryazilim.kutuphane.model.Entity.Book;

public interface BookService {
    Book saveBook(String bookName, String publishName, String subname,String seriname,String isbn, String description, String authorName) throws Exception;
    Book finByBookName(String bookName);
   
    List<BookDto> getAllBook();
    
    BookDto findDtoById(Long id);
    List<BookDto> getAll();
    void delete(long id);
    Book edit(long id,String bookName,
    		 String subname,
    		String seriname,String isbn, String description);
    Book findByisbn(String isbn);
    Book findBySeriName(String seriName);
}
