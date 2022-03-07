package com.crud.library.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long booksId;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private List<Long> bookCopyId;

    }

