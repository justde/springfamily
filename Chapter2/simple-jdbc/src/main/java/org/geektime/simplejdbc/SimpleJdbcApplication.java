package org.geektime.simplejdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@SpringBootApplication
@Slf4j
public class SimpleJdbcApplication implements CommandLineRunner{

    @Autowired
    private FooDao fooDao;

    @Autowired
    private BatchFooDao batchFooDao;

    public static void main(String[] args) {
        SpringApplication.run(SimpleJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //fooDao.insert();
        batchFooDao.batchInsert();
        fooDao.list();
    }

    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate){
        return new SimpleJdbcInsert(jdbcTemplate).withTableName("FOO").usingGeneratedKeyColumns("ID");
    }


}
