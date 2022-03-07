package com.crud.library.book;

import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.service.BookDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookDbServiceTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDbService bookDbService;

    @DisplayName("Test for saving book object")
    @Test
    void testSaveBook() {
        // Given
        Book book = new Book("testTitle", "author", LocalDate.of(1992, 2, 20));
        // When
        bookRepository.save(book);
        // Then
        Long id = book.getBookId();
        Optional<Book> bookOptional = bookRepository.findById(id);
        assertTrue(bookOptional.isPresent());
        // CleanUp
        bookRepository.deleteById(id);
    }

    @DisplayName("Test for deleting book object")
    @Test
    void testDeleteBook() {
        // Given
        Book book = new Book("testTitle", "author", LocalDate.of(1992, 2, 20));
        // When
        bookRepository.save(book);
        // Then
        Long id = book.getBookId();
        bookDbService.deleteBookTitle(id);
        assertEquals(0, bookDbService.getAllBooks().size());

    }

    @DisplayName("Test for getting book object")
    @Test
    void testGetBookById() throws BookNotFoundException {
        // Given
        Book book = new Book("testTitle", "author", LocalDate.of(1992, 2, 20));
        // When
        bookRepository.save(book);
        // Then
        Long id = book.getBookId();
        Book book1 = bookDbService.findBookById(id);
        Assertions.assertEquals("author", book1.getAuthor());
        // CleanUp
        bookRepository.deleteById(id);

    }

    @DisplayName("Test for getting all books objects")
    @Test
    void testGetAllBooks() {
        // Given
        Book book = new Book("testTitle", "author", LocalDate.of(1992, 2, 20));
        Book book1 = new Book("testTitle2", "author2", LocalDate.of(1994, 7, 20));
        Book book2 = new Book("testTitle3", "author3", LocalDate.of(1995, 8, 20));
        bookRepository.save(book);
        bookRepository.save(book1);
        bookRepository.save(book2);
        // When
        List<BookDto> bookList = bookDbService.getAllBooks();
        Long firstBook = book.getBookId();
        Long secondBook = book1.getBookId();
        Long thirdBook = book2.getBookId();
        // Then
        Assertions.assertEquals(3, bookList.size());
        // Then
        bookRepository.deleteById(firstBook);
        bookRepository.deleteById(secondBook);
        bookRepository.deleteById(thirdBook);
    }
}
