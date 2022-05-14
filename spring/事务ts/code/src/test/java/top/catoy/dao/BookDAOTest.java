package top.catoy.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.catoy.domain.Book;

import java.beans.Transient;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:bean.xml")
public class BookDAOTest extends TestCase {
    @Autowired
    private BookDAO bookDAO;

    @Test
    public void test1() {
        Book book = new Book();
        book.setBookName("《水浒传》");
        int i = bookDAO.addBook1(book);
    }

    @Test
    public void test2() {
        Book b = new Book();
        b.setId(14L);
        List<Book> book = bookDAO.getBook1(b);
        System.out.println(book);
    }

    @Test
    public void testRollbackFor() throws FileNotFoundException {
        Book b = new Book();
        b.setBookName("《水浒传》");
        bookDAO.addBook2(b);
    }
}