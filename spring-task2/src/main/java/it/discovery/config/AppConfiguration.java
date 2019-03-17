package it.discovery.config;

import it.discovery.logger.FileLogger;
import it.discovery.logger.Logger;
import it.discovery.logger.MemoryLogger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.BookServiceImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySource("application.properties")
@EnableAsync
public class AppConfiguration {

    @Lazy
    @Bean
    public String myBean() {
        return "";
    }

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

    @Configuration
    public class LoggerConfiguration {

        @Bean
        @Order(Ordered.LOWEST_PRECEDENCE)
        public Logger fileLogger(){
            return new FileLogger();
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public Logger memoryLogger(){
            return new MemoryLogger();
        }
    }
}
