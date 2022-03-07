package com.crud.library.domain.copy;

import com.crud.library.domain.Status;
import com.crud.library.domain.book.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries(
        @NamedQuery(
                name = "Copy.retrieveAvailableCopies",
                query = "FROM Copy where status = 'AVAILABLE'"
        )
)

@NamedNativeQuery(
        name = "Copy.getAvailableBookCopyByBookId",
        query = "SELECT * FROM COPY WHERE BOOK_ID = :ID AND STATUS = 'AVAILABLE'",
        resultClass = Copy.class
)

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COPY")
public class Copy {

    private long bookCopyId;
    private Status status;
    private Book book;

    public Copy(Status status) {
        this.status = status;
    }

    @Id
    @Column(name = "COPY_ID")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getBookCopyId() {
        return bookCopyId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @NotNull
    public Status getStatus() {
        return status;
    }



    @JsonBackReference
    @ManyToOne(cascade =  {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "BOOK_ID")
    @NotNull
    public Book getBook() {
        return book;
    }

}
