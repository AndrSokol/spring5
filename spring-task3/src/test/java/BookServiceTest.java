import it.discovery.config.AppConfiguration;
import it.discovery.model.Book;
import it.discovery.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(AppConfiguration.class)
@ActiveProfiles({"dev", "test"})
public class BookServiceTest {

    @Autowired
    BookService service;

    @Test
    void bookSizeTest(){
        assertEquals(0, service.findBooks().size());
    }

    @Test
    void addBookSizeTest(){
        Book book = new Book();
        book.setName("Introduction into Spring");
        book.setPages(100);
        book.setYear(2016);

        service.saveBook(book);
        assertEquals(1, service.findBooks().size());
    }
}
