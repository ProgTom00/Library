package com.crud.library.mapper;

import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.copy.Copy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookMapper {

    public Book mapToBookTitles(final BookDto bookDto) {
        return new Book(
                bookDto.getBooksId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getReleaseDate(),
                new ArrayList<>()

        );
    }

    public BookDto bookTitlesDto(final Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.getCopyBookList().stream()
                .map(Copy::getBookCopyId)
                .collect(Collectors.toList())

        );
    }
    public List<BookDto> mapToBookTitlesDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::bookTitlesDto)
                .collect(Collectors.toList());
    }
}
