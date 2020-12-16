package com.ozguryazilim.kutuphane.model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String bookName;
    private String description;
    private String subname;
    private String seriname;
    private String isbn;
    private String publisherName;
    private List<AuthorDto> authorDto;


}
