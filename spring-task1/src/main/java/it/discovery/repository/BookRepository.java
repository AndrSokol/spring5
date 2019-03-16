package it.discovery.repository;

import java.util.List;

import it.discovery.model.Book;

public interface BookRepository {
    void init();
    void destroy();
    void saveBook(Book book);
    Book findBookById(int id);
    List<Book> findBooks();
}
