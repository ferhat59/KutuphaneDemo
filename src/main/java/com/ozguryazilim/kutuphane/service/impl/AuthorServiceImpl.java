package com.ozguryazilim.kutuphane.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ozguryazilim.kutuphane.model.Dto.AuthorDto;
import com.ozguryazilim.kutuphane.model.Dto.BookDto;
import com.ozguryazilim.kutuphane.model.Entity.Author;
import com.ozguryazilim.kutuphane.model.Entity.Book;
import com.ozguryazilim.kutuphane.model.Entity.Publisher;
import com.ozguryazilim.kutuphane.repository.AuthorRepo;
import com.ozguryazilim.kutuphane.service.AuthorService;
import com.ozguryazilim.kutuphane.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookService bookService;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authorList = authorRepo.findAll();
        List<AuthorDto> aDtos = new ArrayList<>();
        for (Author author : authorList){
        	AuthorDto dto = new AuthorDto();
            dto.setAuthorName(author.getAuthorName());
            aDtos.add(dto);
        }
        return aDtos;
    }

    @Override
    public Author findByAuthorName(String name) {
        return authorRepo.findByAuthorName(name);
    }



    @Override
    public Author saveAuthor(String authorName,String description) throws Exception{
        Author author = new Author();
        Author entity = authorRepo.findByAuthorName(authorName);
        if (entity != null){
            throw new Exception("Aynı yazar isminden zaten var");
        }
        author.setAuthorName(authorName);
        author.setDescription(description);
        return authorRepo.save(author);
    }

    @Override
    public AuthorDto findByIdAndBookName(Long id, String bookName) {
        Optional<Author> author = authorRepo.findById(id);
        Book book = bookService.finByBookName(bookName);
        AuthorDto dto = new AuthorDto();
        BookDto bookDto = new BookDto();
        if (author.isPresent()){
            Author entity = author.get();
            dto.setId(entity.getId());
            dto.setAuthorName(entity.getAuthorName());
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setPublisherName(book.getPublisher().getPublisherName());
            bookDto.setSeriname(book.getSeriname());
            bookDto.setSubname(book.getSubname());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setDescription(book.getDescription());
            dto.setBookDto(bookDto);
        }
        return dto;
    }

	@Override
	public List<Book> getBook(String name) {
		
		Author yazilanlar= authorRepo.findByAuthorName(name);
		
		return yazilanlar.getBooks();
	}

	@Override
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return authorRepo.findAll();
	}

	@Override
	public void delete(Long id) {
		authorRepo.deleteById(id);
		
	}
}
