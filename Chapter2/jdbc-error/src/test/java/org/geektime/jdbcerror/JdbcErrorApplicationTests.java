package org.geektime.jdbcerror;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcErrorApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test(expected = MySelfError.class)
    public void contextLoads() {
        jdbcTemplate.execute("insert into FOO (ID,BAR) values (1,'ww')");
        jdbcTemplate.execute("insert into FOO (ID,BAR) VALUES (1,'dd')");
    }

}
