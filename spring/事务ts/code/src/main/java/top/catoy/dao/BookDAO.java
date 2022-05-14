package top.catoy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.catoy.domain.Book;
import top.catoy.domain.BookOrder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * timeout-int(秒为单位) 事务超出指定执行时长后自动终止并回滚
     * @param book
     * @return
     */
    @Transactional(timeout = 1)
    public int addBook1(Book book) {
        String sql = "insert into book (book_name) values (?)";
        int update = jdbcTemplate.update(sql, book.getBookName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update;
    }

    /**
     * rollbackFor 指定回滚不会滚的异常
     * @param book
     * @return
     */
    @Transactional(rollbackFor = {FileNotFoundException.class})
    public int addBook2(Book book) throws FileNotFoundException {
        String sql = "insert into book (book_name) values (?)";
        int update = jdbcTemplate.update(sql, book.getBookName());
        FileInputStream fileInputStream = new FileInputStream("xxx");
        return update;
    }

    /**
     * rollbackFor 指定回滚不会滚的异常
     * @param book
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    public int addBook3(Book book) {
        String sql = "insert into book (book_name) values (?)";
        int update = jdbcTemplate.update(sql, book.getBookName());
        System.out.println(1/0);
        return update;
    }

    /**
     * readOnly,设置事务为只读事务，可以进行优化，设置为true可以加快查询速度不用管事务的操作了
     * @param book
     * @return
     */
    @Transactional(readOnly = true)
    public List<Book> getBook1(Book book) {
        String sql = "select * from book where id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), book.getId());
    }
}
