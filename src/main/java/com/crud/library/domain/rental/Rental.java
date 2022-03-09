package com.crud.library.domain.rental;

import com.crud.library.domain.copy.Copy;
import com.crud.library.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Setter
@Entity
@Table(name = "RENTAL")
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    private long rentalId;
    private Copy copy;
    private User user;
    private LocalDate rentFrom;
    private LocalDate rentTo;

    public Rental(LocalDate rentFrom, LocalDate rentTo) {
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "RENTAL_ID", unique = true)
    public long getRentalId() {
        return rentalId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COPY_ID")
    public Copy getCopy() {
        return copy;
    }

    @NotNull
    @Column(name = "RENT_DATE")
    public LocalDate getRentFrom() {
        return rentFrom;
    }

    @NotNull
    @Column(name = "RETURN_DATE")
    public LocalDate getRentTo() {
        return rentTo;
    }

    public Rental(Copy copy, User user, LocalDate rentFrom, LocalDate rentTo) {
        this.copy = copy;
        this.user = user;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }

    public void setBookCopyId(Copy id) {
        this.copy = id;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }
}
