package com.crud.library.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate userDateCreateAccount;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    public long getUserId() {
        return userId;
    }

    @NotNull
    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }
    @NotNull
    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @NotNull
    @Column(name = "REGISTRATION_DATE")
    @JsonFormat(pattern = "YYYY/MM/DD")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getUserDateCreateAccount() {
        return userDateCreateAccount;

    }

    public User(String firstName, String lastName, LocalDate userDateCreateAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userDateCreateAccount = userDateCreateAccount;
    }
}

