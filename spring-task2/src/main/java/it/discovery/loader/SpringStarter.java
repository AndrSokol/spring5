package it.discovery.loader;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;

public class SpringStarter {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml")) {
			
			BookService service = context.getBean("service", BookService.class);
//			service = context.getBean("service");

			int count = context.getBeanDefinitionCount();
			System.out.println("all beans " + count);

			String[] namesBookRepository = context.getBeanNamesForType(BookService.class);
			System.out.println("BookService beans: ");
			for (String name : namesBookRepository){
				System.out.println(name);
			}

			String[] namesDBBookRepository = context.getBeanNamesForType(DBBookRepository.class);
			System.out.println("DBBookRepository beans: ");
			for (String name : namesDBBookRepository){
				System.out.println(name);
			}

			Book book = new Book();
			book.setName("Introduction into Spring");
			book.setPages(100);
			book.setYear(2016);
			service.saveBook(book);

			List<Book> books = service.findBooks();
			System.out.println(books);
		}

	}

}
