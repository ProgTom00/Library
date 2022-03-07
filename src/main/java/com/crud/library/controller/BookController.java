package com.crud.library.controller;


import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.service.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookDbService bookDbService;


    @PostMapping(value = "createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto book) {
        bookDbService.saveBook(book);
    }

    @GetMapping(value = "getBookById")
    public BookDto getBookById(@RequestParam Long bookId) throws BookNotFoundException {
        return bookDbService.findBookByIdLong(bookId);
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteTitle(@RequestParam Long id) {
        bookDbService.deleteBookTitle(id);
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getTitles() {
        return bookDbService.getAllBooks();
    }

    @PutMapping(value = "updateBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookDbService.updateBook(bookDto);
    }
}

