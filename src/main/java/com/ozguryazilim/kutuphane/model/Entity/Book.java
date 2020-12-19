package com.ozguryazilim.kutuphane.model.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "description")
    private String description;
    
    @Column(name = "sub_name")
    private String subname;
    
    @Column(name = "seri_name")
    private String seriname;
    @Column(name = "isbn")
    private String isbn;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "publisher_id") 
    private Publisher publisher; 
    
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "author_book",
        joinColumns = @JoinColumn(name ="book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Author> authors;
}
