package org.example.correctionexercice1.service;

import org.example.correctionexercice1.Dto.BookDtoReceive;
import org.example.correctionexercice1.annotation.Log;
import org.example.correctionexercice1.annotation.Performance;
import org.example.correctionexercice1.entity.Book;
import org.example.correctionexercice1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Log
    @Performance
    public Book save (BookDtoReceive bookDtoReceive){
        Book book = Book.builder()
                .name(bookDtoReceive.getName())
                .author(bookDtoReceive.getAuthor())
                .build();

        return  bookRepository.save(book);
    }

    @Log
    @Performance
    public Book findById(long id){
        return bookRepository.findById(id).orElseThrow();
    }

    @Log
    @Performance
    public List<Book> findAll(){
        return (List<Book>) bookRepository.findAll();
    }

    @Log
    @Performance
    public Book update (long id,BookDtoReceive bookDtoReceive){
        Book book = findById(id);
        book.setName(bookDtoReceive.getName());
        book.setAuthor(bookDtoReceive.getAuthor());

        return  bookRepository.save(book);
    }

    @Log
    @Performance
    public void delete (long id){
        bookRepository.delete(findById(id));
    }
}
