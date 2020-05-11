import com.baltic.road.path.app.RoadPathApplication;
import com.baltic.road.path.app.dto.BookDto;
import com.baltic.road.path.app.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(
        classes = RoadPathApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CrudTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    BookService bookService;

    @Test
    public void addBook() {
        BookDto bookDto = new BookDto("1", "1", "1");

        ResponseEntity<BookDto> response = restTemplate.postForEntity("/book/add", bookDto, BookDto.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(bookDto,bookService.getById(bookDto.getId()));
    }

    @Test
    public void listBooks() {
        BookDto bookDto = new BookDto("1", "2", "2");
        bookService.create(bookDto);

        ResponseEntity<List<BookDto>> response = restTemplate.exchange(
                "/book/list", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(bookDto, response.getBody().get(0));

    }

    @Test
    public void deleteBook() {
        BookDto bookDto = new BookDto("1", "3", "3");
        boolean flag = false;
        if (bookService.listAll().size() == 0) {
            bookService.create(bookDto);
            flag = true;
        } else {
            bookDto = bookService.listAll().get(0);
        }

        ResponseEntity response = restTemplate.exchange(
                "/book/delete/" + bookDto.getId(), HttpMethod.GET, null,
                new ParameterizedTypeReference<Object>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());

        if (flag) {
            assertEquals(0, bookService.listAll().size());
        } else {
            for (BookDto book : bookService.listAll()) {
                assertNotEquals(bookDto, book);
            }
        }
    }

    @Test
    public void getBookById() {
        BookDto bookDto = new BookDto("1", "3", "3");
        if (bookService.listAll().size() == 0) {
            bookService.create(bookDto);
        } else {
            bookDto = bookService.listAll().get(0);
        }

        ResponseEntity<BookDto> response = restTemplate.exchange(
                "/book/" + bookDto.getId(), HttpMethod.GET, null,
                new ParameterizedTypeReference<BookDto>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDto,response.getBody());
    }
}
