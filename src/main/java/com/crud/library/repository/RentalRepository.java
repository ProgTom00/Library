package com.crud.library.repository;

import com.crud.library.domain.rental.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    Rental save(Rental rental);

    @Override
    Optional<Rental> findById(Long id);

    @Override
    void deleteById(Long id);


}
