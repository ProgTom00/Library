package com.crud.library.domain.rental;

import com.crud.library.domain.copy.CopyDto;
import com.crud.library.domain.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    private long id;
    private CopyDto copyDto;
    private UserDto userDto;
    private LocalDate rentFrom;
    private LocalDate rentTo;


}
