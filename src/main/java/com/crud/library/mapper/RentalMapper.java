package com.crud.library.mapper;


import com.crud.library.domain.copy.Copy;
import com.crud.library.domain.copy.CopyDto;
import com.crud.library.domain.rental.Rental;
import com.crud.library.domain.rental.RentalDto;
import com.crud.library.domain.user.User;
import com.crud.library.domain.user.UserDto;
import com.crud.library.repository.CopyRepository;
import com.crud.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RentalMapper {
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private UserRepository userRepository;

    public Rental mapToBookRental(final RentalDto rentalDto, User user, Copy copy) throws NoSuchElementException {
        return new Rental(rentalDto.getId(),
                copy,
                user,
                rentalDto.getRentFrom(),
                rentalDto.getRentTo());

    }

    public RentalDto mapToBooksRentalDto(final Rental rental, UserDto user, CopyDto copy) {
        return new RentalDto(rental.getRentalId(),
                copy,
                user,
                rental.getRentFrom(),
                rental.getRentTo());
    }
}
