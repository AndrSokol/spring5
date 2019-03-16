package it.discovery.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import it.discovery.model.Book;
import lombok.Getter;
import lombok.Setter;

//@Repository
//@Qualifier("xml")
public class XmlBookRepository implements BookRepository {

    private final Map<Integer, Book> books = new HashMap<>();

    private int counter = 0;

    @Getter
    @Setter
    private String file = "book.xml";

    public void init() {
        System.out.println("Started xml repository with file:" + file );
    }

    public void destroy() {
        System.out.println("Shutting down repository ... ");
    }

    public void saveBook(Book book) {
        if (book.getId() == 0) {
            counter++;
            book.setId(counter);
        }

        books.put(book.getId(), book);

        System.out.println("Saved book " + book);
    }

    public Book findBookById(int id) {
        return books.get(id);
    }

    public List<Book> findBooks() {
        return new ArrayList<>(books.values());
    }
}
