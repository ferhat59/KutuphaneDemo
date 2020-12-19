package com.ozguryazilim.kutuphane.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ozguryazilim.kutuphane.Exception.AuthorAddException;
import com.ozguryazilim.kutuphane.Exception.BookException;
import com.ozguryazilim.kutuphane.model.Dto.*;
import com.ozguryazilim.kutuphane.model.Entity.Author;
import com.ozguryazilim.kutuphane.model.Entity.Book;
import com.ozguryazilim.kutuphane.service.AuthorService;
import com.ozguryazilim.kutuphane.service.BookService;
import com.ozguryazilim.kutuphane.service.PublisherService;


import java.util.List;


@Controller
public class MyController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/save-book")
    public String saveBook(@RequestParam(value = "bookName") String bookName,
                           @RequestParam(value = "publisherName") String publisherName, 
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "authorName") String authorName,
                           @RequestParam(value = "subname") String subname,
                           @RequestParam(value = "seriname") String seriname,
                           @RequestParam(value = "isbn") String isbn, 
                           
                           Model model) {
        ExceptionDto errorDto = null;
        try {
            bookService.saveBook( bookName,  publisherName,  subname, seriname, isbn,  description,  authorName);
        } catch (BookException e) {
            errorDto = new ExceptionDto();
            errorDto.setBookexcep(e.getMessage());
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }

        List<PublisherDto> dtos = publisherService.getAll();
        List<AuthorDto> aDtos = authorService.getAll();
        model.addAttribute("publishers", dtos);
        model.addAttribute("authors", aDtos);
        model.addAttribute("error", errorDto);
        return "booksform";
    }

    @GetMapping("/all-books")
    public String getAllAuthor(Model model) {
        List<BookDto> bookDtos = bookService.getAllBook(); 
        List<AuthorDto> aDtos = authorService.getAll();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("aDtos", aDtos);
        
        return "books";
    }

    @GetMapping("/ara/yazarisim")
    public String getTestAllAuthor(Model model,
    		@RequestParam(value = "authorName") String authorName) {
    	
       List<Book> kitaplar= authorService.getBook(authorName);
        model.addAttribute("books", kitaplar);
       model.addAttribute("author", authorName);
       
        return "aranan";
    }
    @GetMapping("/ara/kitapisim")
    public String getBookName(Model model, 
    		@RequestParam(value = "bookName") String bookName) {

       Book kitaplar= bookService.finByBookName(bookName);
        model.addAttribute("books", kitaplar);
        
       
        return "aranan";
    }
    @GetMapping("/ara/isbn")
    public String getisbn(Model model, @RequestParam(value = "isbn") String isbn  ) {

      Book kitaplar = bookService.findByisbn(isbn);
        model.addAttribute("books", kitaplar);
        
      
        return "aranan";
    }
    
    @GetMapping("/ara/seri")
    public String getseri(Model model, @RequestParam(value = "seri") String seriName ) {
System.out.println(seriName);
      Book book = bookService.findBySeriName(seriName);
     
        model.addAttribute("books", book);
        
       
        return "aranan";
    }

    @GetMapping("/search")
    public String getTest(Model model) {
        List<BookDto> bookDtos = bookService.getAllBook(); 
        List<AuthorDto> aDtos = authorService.getAll();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("aDtos", aDtos);
        return "search";
    }

    @GetMapping("/save-publisher")
    public String savePublisher(@RequestParam(value = "publisherName") String publisherName,
    		@RequestParam(value = "description") String description,
    		Model model) throws Exception {
        ExceptionDto errorDto = null;
        try {
            publisherService.savePublisher(publisherName,description);
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }
        model.addAttribute("error", errorDto);
        return "publisher";
    }

    @GetMapping("/save-author")
    public String saveAuthor(@RequestParam(value = "authorName") String authorName,
    		@RequestParam(value = "description") String description,
    		Model model) {
        ExceptionDto errorDto = null;
        try {
            authorService.saveAuthor(authorName,description);
        } catch (Exception e) {
            errorDto = new ExceptionDto(e.getMessage());
        }
        model.addAttribute("error", errorDto);
        return "authorAdd";
    }

    @GetMapping("/book/delete/{bookId}")
    public String deleteBook(Model model, @PathVariable(value = "bookId") Long bookId){
      
    	bookService.delete(bookId);
        return "authors";
    }

    @GetMapping("/book/edit/{bookId}")
    public String addAuthorForBook(Model model, @PathVariable(value = "bookId") Long bookId){
        BookDto bookDto = bookService.findDtoById(bookId);
        List<AuthorDto> aDtos = authorService.getAll();
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("authors", aDtos);
        model.addAttribute("id", bookId);
        return "editBook";
    }
    @GetMapping("/editBook")
    public String editBook(
    		@RequestParam(value = "id") Long id,
    		@RequestParam(value = "booksName") String bookName,
             @RequestParam(value = "description") String description,
            
            @RequestParam(value = "subname") String subname,
            @RequestParam(value = "seriname") String seriname,
            @RequestParam(value = "isbn") String isbn, 
            
            Model model) {
        ExceptionDto errorDto = null;
      try {
    	  bookService.edit(id, bookName, subname, seriname, isbn, description);
      }
        	catch (Exception e) {
				errorDto = new ExceptionDto("Düzenleme yapılamadı");
			}
            
        
        List<BookDto> bookDtos = bookService.getAllBook();
        model.addAttribute("bookDtos", bookDtos);
        model.addAttribute("error", errorDto);
        return "books";
    }
    @GetMapping("/all-authors")
    public String authors(Model model){
    
      
    	model.addAttribute("authors", authorService.findAll());
        return "authors";
    }
    @GetMapping("/authors/delete/{authorId}")
    public String deleteauthors(Model model, @PathVariable(value = "authorId") Long authorId){
      System.out.println("silindi");
      ExceptionDto dto=null;
    	try {
    		authorService.delete(authorId);
    	}catch (Exception e) {
			dto =new ExceptionDto("Yazara ait kitap olduğundan silinemiyor");
		}
    	model.addAttribute("error", dto);
        return "authors";
    }
    }

