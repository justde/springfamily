package oeg.geektime.transactionpropagation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@Slf4j
public class TransactionPropagationApplication implements CommandLineRunner {
    @Autowired
    public FooService fooService;
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TransactionPropagationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {

        }
        List<Foo> query = jdbcTemplate.query("select * from FOO", new RowMapper<Foo>() {

            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder().bar(resultSet.getString(2)).id(resultSet.getInt(1)).build();
            }
        });
        query.forEach(a->log.info("value:{}",a));
        log.info("over");
    }
}
