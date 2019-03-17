package it.discovery;

import it.discovery.config.AppConfiguration;
import it.discovery.model.Book;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MainApplication {
//
//	@Autowired
//	static BookService service;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(
				MainApplication.class, args);
//
//		context.getEnvironment().setActiveProfiles("dev", "test");
//		context.register(AppConfiguration.class);
//		context.refresh();


		BookService service = context.getBean(BookService.class);
		Book book = new Book();
		book.setName("Introduction into Spring");
		book.setPages(100);
		book.setYear(2016);
		service.saveBook(book);

		List<Book> books = service.findBooks();
		System.out.println(books);

//		System.out.println("Total bean count: " + context.getBeanDefinitionCount());
//		System.out.println("Our beans: " +
//				Arrays.stream(context.getBeanDefinitionNames())
//						.map(context::getBean)
//						.map(Object::getClass)
//						.map(Class::getSimpleName)
//						.collect(Collectors.joining(",")))
//		;
	}
}
