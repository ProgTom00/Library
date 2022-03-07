package com.crud.library.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate userDateCreateAccount;

}
