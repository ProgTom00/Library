package com.crud.library.domain.book;

import com.crud.library.domain.copy.Copy;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Table(name = "BOOKS")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private long bookId;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private List<Copy> copyBookList = new ArrayList<>();

    public Book(String title, String author, LocalDate releaseDate, List<Copy> copyBookList) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.copyBookList = copyBookList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "BOOKS_ID",unique = true)
    public long getBookId() {
        return bookId;
    }

    @NotNull
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @NotNull
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @NotNull
    @Column(name = "RELEASE_DATE")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @JsonManagedReference
    @OneToMany(targetEntity = Copy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public List<Copy> getCopyBookList() {
        return copyBookList;
    }


    public Book(String title, String author, LocalDate releaseDate) {

        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.copyBookList = new ArrayList<>();
    }

}
