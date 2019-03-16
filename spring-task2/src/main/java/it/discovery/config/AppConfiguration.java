package it.discovery.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public BookService bookService(@Qualifier("xml") BookRepository repository){
        return new BookServiceImpl(repository);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("db")
    public BookRepository dbBookRepository(){
        return new DBBookRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("xml")
    public BookRepository xmlBookRepository(){
        return new XmlBookRepository();
    }
}
