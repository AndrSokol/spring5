package it.discovery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {


	private final BookRepository repository;

	@Autowired

	public BookServiceImpl(@Qualifier("db") BookRepository repository) {
		this.repository = repository;
		System.out.println("Using repository " + repository.getClass().getSimpleName());
	}
	
	public void saveBook(Book book) {
		repository.saveBook(book);
	}
	
	public Book findBookById(int id) {
		return repository.findBookById(id);
	}

	public List<Book> findBooks() {
		return repository.findBooks();
	}
}
