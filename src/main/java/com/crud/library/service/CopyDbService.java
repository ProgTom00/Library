package com.crud.library.service;

import com.crud.library.domain.book.Book;
import com.crud.library.domain.book.BookDto;
import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.domain.copy.Copy;
import com.crud.library.domain.copy.CopyDto;
import com.crud.library.domain.copy.CopyNotFoundException;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopyDbService {

    @Autowired
    private final CopyRepository copyRepository;
    @Autowired
    private final CopyMapper copyMapper;
    @Autowired
    private final BookDbService bookDbService;


    public Copy saveBookCopy(CopyDto bookCopy) throws BookNotFoundException {
        Book book = bookDbService.findBookById(bookCopy.getBookId().getBooksId());
        Copy copy1 = copyMapper.mapToBookCopy(bookCopy, book);
        return copyRepository.save(copy1);

    }

    public List<Copy> getAllCopyBooks() {
        return copyRepository.findAll();
    }

    public void deleteBookCopy(final Long bookCopyId) {
        copyRepository.deleteById(bookCopyId);

    }

    public CopyDto updateCopyBook(CopyDto copyDto) throws CopyNotFoundException, BookNotFoundException {
        Optional<Copy> copy1 = copyRepository.findById(copyDto.getBookCopyId());
        copy1.orElseThrow(CopyNotFoundException::new).setStatus(copyDto.getStatus());
        copyRepository.save(copy1.orElseThrow(CopyNotFoundException::new));
        Copy copy2 = copyRepository.findById(copyDto.getBookCopyId()).orElseThrow(CopyNotFoundException::new);
        copy2.setStatus(copyDto.getStatus());
        BookDto bookDto = bookDbService.findBookByIdLong(copy2.getBook().getBookId());
        copyDto = copyMapper.mapToBookCopyDto(copy2, bookDto);
        Copy savedBook2 = saveBookCopy(copyDto);
        BookDto savedBookDto2 = bookDbService.findBookByIdLong(savedBook2.getBook().getBookId());
        return copyMapper.mapToBookCopyDto(savedBook2, savedBookDto2);
    }

    public CopyDto getCopyById (Long id) throws BookNotFoundException, CopyNotFoundException {
        Copy copy = copyRepository.findById(id).orElseThrow(CopyNotFoundException::new);
        BookDto bookDto = bookDbService.findBookByIdLong(copy.getBook().getBookId());
        return copyMapper.mapToBookCopyDto(copy, bookDto);
    }

    public List<CopyDto> getAvailableBookCopyById(String title) throws BookNotFoundException, CopyNotFoundException {
        BookDto bookDto = bookDbService.findBookByTitleLong(title);
        List<Copy> list = copyRepository.getAvailableBookCopyByBookId(bookDto.getBooksId());
        if (list.isEmpty()) {
            throw new CopyNotFoundException();
        } else {
            return copyMapper.mapToBookCopyDtoList(list, bookDto);
        }
    }

}
