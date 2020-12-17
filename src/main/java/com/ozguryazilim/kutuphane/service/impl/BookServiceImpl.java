package com.ozguryazilim.kutuphane.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozguryazilim.kutuphane.Exception.AuthorAddException;
import com.ozguryazilim.kutuphane.Exception.BookException;
import com.ozguryazilim.kutuphane.model.Dto.AuthorDto;
import com.ozguryazilim.kutuphane.model.Dto.BookDto;
import com.ozguryazilim.kutuphane.model.Entity.Author;
import com.ozguryazilim.kutuphane.model.Entity.Book;
import com.ozguryazilim.kutuphane.model.Entity.Publisher;
import com.ozguryazilim.kutuphane.repository.BookRepo;
import com.ozguryazilim.kutuphane.service.AuthorService;
import com.ozguryazilim.kutuphane.service.BookService;
import com.ozguryazilim.kutuphane.service.PublisherService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;

    @Override
    public Book saveBook(String bookName, String publishName, String subname,String seriname,String isbn, String description, String authorName) throws Exception {
        Book book = new Book();
        Book entities = bookRepo.findByBookName(bookName);
        if (entities != null){
            throw new BookException("Kitap zaten Var");
        }
        book.setBookName(bookName);
        book.setIsbn(isbn);
        book.setSubname(subname);
        book.setSeriname(seriname);
        book.setDescription(description);
        Author author = authorService.findByAuthorName(authorName);
        if (author == null){
            throw new Exception("Yazar Seç");
        }
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        book.setAuthors(authorList);
        
        Publisher publisher = publisherService.findByName(publishName);
        if (publisher == null){
            throw new Exception("выберите издателя");
        }
        book.setPublisher(publisher);
        Book entity = bookRepo.save(book);
        return entity;
    }

    @Override
    public Book finByBookName(String bookName) {
        return bookRepo.findByBookName(bookName);
    }

   


    @Override
    public List<BookDto> getAllBook() {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookList = bookRepo.findAll();
        for (Book book : bookList){
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setDescription(book.getDescription());
            bookDto.setSeriname(book.getSeriname());
            bookDto.setSubname(book.getSubname());
            bookDto.setIsbn(book.getIsbn());
            Publisher publisher = book.getPublisher();
            bookDto.setPublisherName(publisher.getPublisherName());
            List<Author> authorList = book.getAuthors();
            List<AuthorDto> authorDto2s = new ArrayList<>();
            for (Author author : authorList){
                AuthorDto authorDto2 = new AuthorDto();
                authorDto2.setId(author.getId());
                authorDto2.setAuthorName(author.getAuthorName());
                authorDto2s.add(authorDto2);
            }
            bookDto.setAuthorDto(authorDto2s);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public BookDto findDtoById(Long id) {
        BookDto bookDto = new BookDto();
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()){
            Book entity = book.get();
            bookDto.setBookName(entity.getBookName());
            bookDto.setDescription(entity.getDescription());
            bookDto.setSeriname(entity.getSeriname());
            bookDto.setSubname(entity.getSubname());
            bookDto.setIsbn(entity.getIsbn());
        }
        return bookDto;
    }

  /*  @Override
    public List<BookDto> getTestAllBook(String bookName, String authorName) {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookList = null;
        if ("All".equals(bookName)){
            bookList = bookRepo.findAll();
        } else {
            Book book = bookRepo.findByBookName(bookName);
            bookList = new ArrayList<>();
            bookList.add(book);
        }
        for (Book book : bookList){
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setBookName(book.getBookName());
            bookDto.setDescription(book.getDescription());
            bookDto.setSeriname(book.getSeriname());
            bookDto.setSubname(book.getSubname());
            bookDto.setIsbn(book.getIsbn());
            Publisher publisher = book.getPublisher();
            bookDto.setPublisherName(publisher.getPublisherName());
            List<Author> authorList = book.getAuthors();
            List<AuthorDto> authorDto2s = new ArrayList<>();
            for (Author author : authorList){
                AuthorDto authorDto2 = new AuthorDto();
                if ("All".equals(authorName)){
                    authorDto2.setId(author.getId());
                    authorDto2.setAuthorName(author.getAuthorName());
                    authorDto2s.add(authorDto2);
                } else {
                    if (authorName.equals(author.getAuthorName())){
                        authorDto2.setId(author.getId());
                        authorDto2.setAuthorName(author.getAuthorName());
                        authorDto2s.add(authorDto2);
                    }
                }

            }
            bookDto.setAuthorDto(authorDto2s);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }
*/
    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = bookRepo.findAll();
        List<BookDto> dtos = new ArrayList<>();
        for (Book book : bookList){
        	BookDto dto = new BookDto();
            dto.setBookName(book.getBookName());
            dtos.add(dto);
        }
        return dtos;
    }

	@Override
	public void delete(long id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public Book edit(long id, String bookName,  String subname, String seriname, String isbn,
			String description) {
		// TODO Auto-generated method stub
		Book k =  bookRepo.getOne(id);
	
	k.setBookName(bookName);
	k.setDescription(description);
	k.setIsbn(isbn);
	k.setSeriname(seriname);
	k.setSeriname(seriname);
	k.setSubname(subname);
	return bookRepo.save(k);
		
	}

	@Override
	public Book findByisbn(String isbn) {
		// TODO Auto-generated method stub
		return bookRepo.findByisbn(isbn);
	}

	@Override
	public Book findBySeriName(String seriName) {
		// TODO Auto-generated method stub
		return bookRepo.findByseriname(seriName);
	}
}
