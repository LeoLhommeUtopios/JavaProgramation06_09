package org.example.correctionexercice1.controller;

import org.apache.coyote.Response;
import org.example.correctionexercice1.Dto.BookDtoReceive;
import org.example.correctionexercice1.entity.Book;
import org.example.correctionexercice1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id")long id){
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll (){
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> create (@RequestBody BookDtoReceive bookDtoReceive){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable("id")long id){
        bookService.delete(id);
        return ResponseEntity.ok("Livre supprimé");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update (@PathVariable long id,@RequestBody BookDtoReceive bookDtoReceive){
        return ResponseEntity.ok(bookService.update(id,bookDtoReceive));
    }
}
