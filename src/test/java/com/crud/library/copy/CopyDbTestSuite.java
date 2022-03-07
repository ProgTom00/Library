package com.crud.library.copy;

import com.crud.library.domain.Status;
import com.crud.library.domain.book.Book;
import com.crud.library.domain.copy.Copy;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.CopyRepository;
import com.crud.library.service.CopyDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class CopyDbTestSuite {

    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CopyDbService copyDbService;


    @DisplayName("Test for BookCopy objects")
    @Test
    void testBookSaveWithBookCopy() {
        // Given
        Copy copy = new Copy(Status.AVAILABLE);
        Copy copy1 = new Copy(Status.AVAILABLE);
        Copy copy2 = new Copy(Status.RENTED);
        Copy copy3 = new Copy(Status.AVAILABLE);
        Copy copy4 = new Copy(Status.LOST);
        Book book = new Book("testTitle", "testAuthor", LocalDate.of(1992, 2, 20));
        Book book1 = new Book("bookTitle1", "bookAuthor1", LocalDate.of(1995, 2, 20));
        book.getCopyBookList().add(copy);
        book.getCopyBookList().add(copy3);
        book.getCopyBookList().add(copy1);
        book1.getCopyBookList().add(copy2);
        book1.getCopyBookList().add(copy4);
        copy.setBook(book);
        copy3.setBook(book);
        copy2.setBook(book);
        copy1.setBook(book1);
        copy4.setBook(book1);
        // When
        bookRepository.save(book);
        bookRepository.save(book1);
        // Then
        Long id = book.getBookId();
        Long id2 = book1.getBookId();
        assertNotEquals(0, id);
        assertNotEquals(0, id2);
        // CleanUp
        bookRepository.deleteById(id);
        bookRepository.deleteById(id2);

    }

    @DisplayName("Test for getting all copies books objects")
    @Test
    void getAllCopies() {
        // Given
        Copy copy = new Copy(Status.AVAILABLE);
        Copy copy1 = new Copy(Status.LOST);
        Book book = new Book("testTitle", "testAuthor", LocalDate.of(1992, 2, 20));
        book.getCopyBookList().add(copy);
        book.getCopyBookList().add(copy1);
        copy.setBook(book);
        copy1.setBook(book);
        // When
        bookRepository.save(book);
        // Then
        List<Copy> t = copyDbService.getAllCopyBooks();
        assertEquals(2, t.size());
        // CleanUp
        bookRepository.deleteById(book.getBookId());

    }
}