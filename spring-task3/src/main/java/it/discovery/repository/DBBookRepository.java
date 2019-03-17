package it.discovery.repository;

import it.discovery.model.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database-related book operations
 * 
 * @author morenets
 *
 */

//@Repository
//@Qualifier("db")
@RequiredArgsConstructor
@ConfigurationProperties("user")
@Component
@Getter
@Setter
public class DBBookRepository implements BookRepository{
	private final Map<Integer, Book> books = new HashMap<>();

	private int counter = 0;

	private final String server;
	private final String db;

	private String url;
	private String login;
	private String password;
	
	public void init() {
		System.out.println("!!! Started db repository with server:" + server + " and database: " + db );
		System.out.println(String.format("!!!!!! url %s, login %s, pass %s", url, login, password));
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
