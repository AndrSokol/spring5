package it.discovery.service;

import java.util.List;

import it.discovery.events.LogEvent;
import it.discovery.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;

//@Service
public class BookServiceImpl implements BookService {


	private final BookRepository repository;

//	@Autowired
//	List<Logger> loggers;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

//	@Autowired
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
		System.out.println("Using repository " + repository.getClass().getSimpleName());
	}
	
	public void saveBook(Book book) {
		repository.saveBook(book);
//		loggers.forEach(logger -> logger.log("Added book " + book));
		eventPublisher.publishEvent(new LogEvent(this, "Saved book: " + book));
	}
	
	public Book findBookById(int id) {
		return repository.findBookById(id);
	}

	public List<Book> findBooks() {
		return repository.findBooks();
	}
}
