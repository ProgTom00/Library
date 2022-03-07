package com.crud.library.repository;

import com.crud.library.domain.copy.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {

    @Override
    List<Copy> findAll();

    @Override
    Copy save(Copy copy);

    Optional<Copy> findById(Long id);

    @Query
    List<Copy> retrieveAvailableCopies();

    @Query(nativeQuery = true)
    List<Copy> getAvailableBookCopyByBookId(@Param("ID") Long bookId);

}
