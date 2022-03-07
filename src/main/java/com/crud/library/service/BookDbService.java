package com.crud.library.service;


import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDbService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final BookMapper bookMapper;


    public Book saveBook(final BookDto bookdto) {
        Book book = bookMapper.mapToBookTitles(bookdto);
        return bookRepository.save(book);
    }

    public List<BookDto> getAllBooks() {
        List<Book> bookDto = bookRepository.findAll();
        return bookMapper.mapToBookTitlesDtoList(bookDto);

    }

    public Book findBookById(final Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

    }
    public Book findBookDtoByTitleLong(final String title) throws BookNotFoundException {
        return bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
    }

    public BookDto findBookByTitleLong(final String title) throws BookNotFoundException {
        Book book = bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
        return bookMapper.bookTitlesDto(book);

    }
    public BookDto findBookByIdLong(final Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookMapper.bookTitlesDto(book);
    }

    public void deleteBookTitle(Long bookTitleId) {
        bookRepository.deleteById(bookTitleId);
    }
    public BookDto updateBook(final BookDto bookDto) {
        Book bookDto1 = saveBook(bookDto);
        return bookMapper.bookTitlesDto(bookDto1);
    }
}
