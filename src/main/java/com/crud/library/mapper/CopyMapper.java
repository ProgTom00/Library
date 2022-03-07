package com.crud.library.mapper;


import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.copy.Copy;
import com.crud.library.domain.copy.CopyDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CopyMapper {


    public Copy mapToBookCopy(final CopyDto copyDto, final Book book) {
        return new Copy(
                copyDto.getBookCopyId(),
                copyDto.getStatus(),
                book

        );
    }
    public CopyDto mapToBookCopyDto(final Copy copy, final BookDto bookDto) {
        return new CopyDto(
                copy.getBookCopyId(),
                copy.getStatus(),
                bookDto
        );
    }
    public List<CopyDto> mapToBookCopyDtoList(final List<Copy> copyDtoList, final BookDto bookDto) {
        List<CopyDto> result = new ArrayList<>();
        copyDtoList.forEach(b -> result.add(mapToBookCopyDto(b, bookDto)));

        return result;
    }
}
