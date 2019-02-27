package org.geektime.transactiodemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class TransactioDemoApplication implements CommandLineRunner {
    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TransactioDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        fooService.insertRecord();
        log.info("{bbbb}:{}",jdbcTemplate.queryForObject("select count(*) from FOO where BAR = 'AAA'",Long.class));

        try{
            fooService.insertThenRollback();
        }catch (Exception e){
            log.info("{bbbb}:{}",jdbcTemplate.queryForObject("select count(*) from FOO where BAR = 'BBB'",Long.class));
        }


        try{
            fooService.invertInsertThenRollback();
        }catch (Exception e){
            log.info("{bbbb}:{}",jdbcTemplate.queryForObject("select count(*) from FOO where BAR = 'BBB'",Long.class));
        }

    }
}
