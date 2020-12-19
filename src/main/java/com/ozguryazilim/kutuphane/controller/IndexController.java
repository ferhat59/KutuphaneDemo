package com.ozguryazilim.kutuphane.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ozguryazilim.kutuphane.model.Dto.AuthorDto;
import com.ozguryazilim.kutuphane.model.Dto.BookDto;
import com.ozguryazilim.kutuphane.model.Dto.PublisherDto;
import com.ozguryazilim.kutuphane.service.AuthorService;
import com.ozguryazilim.kutuphane.service.BookService;
import com.ozguryazilim.kutuphane.service.PublisherService;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {

		return "login";
	}

	@GetMapping("/access_denied")
	public String showAccessDenied() {
		
		return "access_denied";
		
	}
    @GetMapping("/books")
    public String getProduct(){
        return "books";
    }

    @RequestMapping("/book/new")
    public String newProduct(Model model){
        List<PublisherDto> dtos = publisherService.getAll();
        List<AuthorDto> aDtos = authorService.getAll();
        model.addAttribute("publishers", dtos);
        model.addAttribute("authors", aDtos);
        return "booksform";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Model model){
        return "publisher";
    }

    @GetMapping("/add-author")
    public String addAuthor(Model model){
        return "authorAdd";
    }
    @GetMapping("/get-authors")
    public String authors(Model model){
        return "authors";
    }
   

   
}
