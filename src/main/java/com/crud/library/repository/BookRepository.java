package com.crud.library.repository;

import com.crud.library.domain.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Book save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByTitle(String title);

    @Override
    void deleteById(Long id);


}
