package com.crud.library.service;


import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.domain.copy.CopyDto;
import com.crud.library.domain.copy.CopyNotFoundException;
import com.crud.library.domain.rental.Rental;
import com.crud.library.domain.rental.RentalDto;
import com.crud.library.domain.rental.RentalNotFoundException;
import com.crud.library.domain.user.UserDto;
import com.crud.library.domain.user.UserNotFoundException;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {

    @Autowired
    private final RentalRepository rentalRepository;
    @Autowired
    private final UserDbService userDbService;
    @Autowired
    private final CopyDbService copyDbService;
    @Autowired
    private final RentalMapper mapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final CopyMapper copyMapper;
    @Autowired
    private final BookDbService bookDbService;

    public Rental saveRental(final Rental rental) {
        return rentalRepository.save(rental);

    }

    public void deleteRentalBookCopy(Long id) throws CopyNotFoundException, RentalNotFoundException, BookNotFoundException, UserNotFoundException {
        RentalDto rentalDto = getRentalCopyBook(id);
        copyDbService.updateCopyBook(rentalDto.getCopyDto());
        rentalRepository.deleteById(id);
    }

    public RentalDto getRentalCopyBook(Long id) throws RentalNotFoundException, CopyNotFoundException, UserNotFoundException, BookNotFoundException {
        Rental rentalBookCopy = rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
        CopyDto copyDto = copyDbService.getCopyById(rentalBookCopy.getCopy().getBookCopyId());
        UserDto userDto = userDbService.getUser(rentalBookCopy.getUser().getUserId());

        return mapper.mapToBooksRentalDto(rentalBookCopy, userDto, copyDto);
    }

    public void processRentBook(Long userId, String title, LocalDate date) throws CopyNotFoundException, BookNotFoundException, UserNotFoundException {
        List<CopyDto> list = copyDbService.getAvailableBookCopyById(title);
        if (!list.isEmpty()) {
            Long copyId = list.get(0).getBookCopyId();
            CopyDto copyDto = copyDbService.getCopyById(copyId);
            UserDto userDto = userDbService.getUser(userId);
            Book book = bookDbService.findBookDtoByTitleLong(title);
            Rental rental = new Rental(copyMapper.mapToBookCopy(copyDto, book),
                    userMapper.mapToUser(userDto), LocalDate.now(), date);
            saveRental(rental);
            copyDbService.updateCopyBook(copyDto);
        }
    }
}




