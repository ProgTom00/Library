package com.crud.library.controller;

import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.domain.copy.CopyNotFoundException;
import com.crud.library.domain.rental.RentalNotFoundException;
import com.crud.library.domain.user.UserNotFoundException;
import com.crud.library.service.RentalDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/v1/library")
@RestController
@CrossOrigin(origins = "*")
public class RentalController {

    @Autowired
    private RentalDbService rentalDbService;

    @PostMapping(value = "rentedBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void rentBook(@RequestParam Long userId, @RequestParam String title, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentUntil)
            throws CopyNotFoundException, UserNotFoundException, BookNotFoundException {
        rentalDbService.processRentBook(userId, title, rentUntil);
    }

    @DeleteMapping(value = "deleteRental")
    public void returnCopyBook(@RequestParam Long id) throws RentalNotFoundException, BookNotFoundException, CopyNotFoundException, UserNotFoundException {
        rentalDbService.deleteRentalBookCopy(id);
    }

    @GetMapping(value = "getRental")
    public void returnBook(@RequestParam Long id) throws UserNotFoundException, CopyNotFoundException, RentalNotFoundException, BookNotFoundException {
        rentalDbService.getRentalCopyBook(id);
    }
}
