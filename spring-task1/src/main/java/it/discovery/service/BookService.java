package it.discovery.service;

import java.util.List;

import it.discovery.model.Book;

public interface BookService {
     void saveBook(Book book);
     Book findBookById(int id);
     List<Book> findBooks();
}
