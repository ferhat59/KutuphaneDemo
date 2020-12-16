package com.ozguryazilim.kutuphane.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilim.kutuphane.model.Entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    Book findByBookName(String name);
    Book findByisbn(String isbn);
    Book findByseriname(String seriName);
}
