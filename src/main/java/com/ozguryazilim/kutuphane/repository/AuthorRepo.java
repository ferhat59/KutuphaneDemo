package com.ozguryazilim.kutuphane.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilim.kutuphane.model.Entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByAuthorName(String name);
}
