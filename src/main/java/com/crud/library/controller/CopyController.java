package com.crud.library.controller;


import com.crud.library.domain.book.BookNotFoundException;
import com.crud.library.domain.copy.CopyDto;
import com.crud.library.domain.copy.CopyNotFoundException;
import com.crud.library.service.CopyDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
@CrossOrigin(origins = "*")
public class CopyController {

    @Autowired
    private CopyDbService copyDbService;

    @PostMapping(value = "createCopyBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopyDto copyDto) throws BookNotFoundException {
        copyDbService.saveBookCopy(copyDto);
    }

    @PutMapping(value = "updateCopyBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCopyBook(@RequestBody CopyDto copyDto) throws CopyNotFoundException, BookNotFoundException {
        copyDbService.updateCopyBook(copyDto);
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long id) {
        copyDbService.deleteBookCopy(id);
    }

    @GetMapping(value = "getCopy")
    public CopyDto getCopy(@RequestParam Long id) throws CopyNotFoundException, BookNotFoundException {
        return copyDbService.getCopyById(id);
    }

    @GetMapping(value = "getCopyByTitle")
    public List<CopyDto> getAvailableCopies(@RequestParam String title) throws CopyNotFoundException, BookNotFoundException {
        return copyDbService.getAvailableBookCopyById(title);
    }
}


