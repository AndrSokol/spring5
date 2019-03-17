package it.discovery.service;

import it.discovery.annotations.Init;
import it.discovery.events.LogEvent;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

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

	@Init
	public void init(){
		System.out.println("Service Init");
	}
}
