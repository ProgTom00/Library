package com.crud.library.domain.copy;

import com.crud.library.domain.Status;
import com.crud.library.domain.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CopyDto {
    private long bookCopyId;
    private Status status;
    private BookDto bookId;
}
