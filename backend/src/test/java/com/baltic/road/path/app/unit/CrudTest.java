package com.baltic.road.path.app.unit;

import com.baltic.road.path.app.RoadPathApplication;
import com.baltic.road.path.app.dao.BookDao;
import com.baltic.road.path.app.dto.BookDto;
import com.baltic.road.path.app.extensions.Book;
import com.baltic.road.path.app.extensions.BookEx;
import com.baltic.road.path.app.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        classes = RoadPathApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(BookEx.class)
public class CrudTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    BookService bookService;

    @Autowired
    BookDao bookDao;

    @Value("${secret}")
    private String secretKey;

    @BeforeEach
    void clean() {
        bookDao.deleteAll(secretKey);
    }

    @Test
    @DisplayName("Add book by rest api")
    void addBook(@Book(id = "1", token = "1", title = "1") BookDto bookDto) {
        ResponseEntity<BookDto> response = restTemplate.postForEntity("/book/add", bookDto, BookDto.class);
        assertNotNull(response, "Response must be not null");
        BookDto book = response.getBody();
        assertNotNull(book, "Body in response must be not null");
        assertNotNull(book.getId(), "Id in received book must be not null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code must be OK (200)");
        assertEquals(bookDto, bookService.getById(book.getId()));
    }

    @Test
    @DisplayName("Get book list by rest api")
    public void listBooks(@Book(id = "1", token = "2", title = "2") BookDto bookDto) {
        bookService.create(bookDto);
        ResponseEntity<List<BookDto>> response = restTemplate.exchange(
                "/book/list", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
                });
        assertNotNull(response, "Response must be not null");
        List<BookDto> bookList = response.getBody();
        assertNotNull(bookList, "Body in response must be not null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code must be OK (200)");
        assertEquals(1, bookList.size(), "List<Book> size must be eq 1");
        assertEquals(bookDto, bookList.get(0), "BookDto must be eq received book");
    }

    @Test
    @DisplayName("delete book by rest api")
    public void deleteBook(@Book(id = "1", token = "3", title = "3") BookDto bookDto) {
        bookService.create(bookDto);
        ResponseEntity<Void> response = restTemplate.exchange(
                "/book/delete/" + bookDto.getId(), HttpMethod.GET, null,
                new ParameterizedTypeReference<Void>() {
                });
        assertNotNull(response, "Response must be not null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code must be OK (200)");
        assertTrue(bookService.listAll().isEmpty(), "List must be empty");
    }

    @Test
    @DisplayName("Get book by id by rest api")
    public void getBookById(@Book(id = "1", token = "3", title = "3") BookDto bookDto) {
        bookService.create(bookDto);
        ResponseEntity<BookDto> response = restTemplate.exchange(
                "/book/" + bookDto.getId(), HttpMethod.GET, null,
                new ParameterizedTypeReference<BookDto>() {
                });
        assertNotNull(response, "Response must be not null");
        assertNotNull(response.getBody(), "Body in response must be not null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code must be OK (200)");
        assertEquals(bookDto, response.getBody(), "Original Book must be eq received book");
    }
}
