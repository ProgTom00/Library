package com.crud.library.rental;

import com.crud.library.domain.Status;
import com.crud.library.domain.book.Book;
import com.crud.library.domain.copy.Copy;
import com.crud.library.domain.rental.Rental;
import com.crud.library.domain.user.User;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.CopyRepository;
import com.crud.library.repository.RentalRepository;
import com.crud.library.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class RentalDbTestSuite {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    CopyRepository copyRepository;


    @Transient
    @Test
    void testRentalSave() {
        // Given
        Book book = new Book("test1", "test2", LocalDate.of(1992, 10, 10));
        Copy copy = new Copy(Status.AVAILABLE);
        copy.setBook(book);
        book.getCopyBookList().add(copy);
        User user = new User("name", "lastName", LocalDate.now());
        Rental booksRental = new Rental(LocalDate.now(), LocalDate.now().plusDays(15));
        booksRental.setBookCopyId(copy);
        booksRental.setUserId(user);
        // When
        bookRepository.save(book);
        userRepository.save(user);
        rentalRepository.save(booksRental);
        // Then
        Long userId = user.getUserId();
        Long bookCopyId = copy.getBookCopyId();
        Long bookRentalId = booksRental.getRentalId();

        Optional<Rental> rental = rentalRepository.findById(bookRentalId);
        Assertions.assertTrue(rental.isPresent());
        Optional<User> user1 = userRepository.findById(userId);
        Assertions.assertTrue(user1.isPresent());
        Optional<Copy> copy1 = copyRepository.findById(bookCopyId);
        Assertions.assertTrue(copy1.isPresent());
        // CleanUp
        rentalRepository.deleteById(bookRentalId);
        userRepository.deleteById(userId);
        copyRepository.deleteById(bookCopyId);

    }
}