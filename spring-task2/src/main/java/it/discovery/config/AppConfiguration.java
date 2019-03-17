package it.discovery.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
public class AppConfiguration {

    @Bean
    public BookService bookService(@Qualifier("db") BookRepository repository){
        return new BookServiceImpl(repository);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("db")
    @Profile("dev")
    public BookRepository dbBookRepository(Environment env){
        return new DBBookRepository(env.getProperty("book.server", "loc"),
                env.getProperty("book.db", "ts"));
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("xml")
    @Profile("test")
    public BookRepository xmlBookRepository(){
        return new XmlBookRepository();
    }
}
