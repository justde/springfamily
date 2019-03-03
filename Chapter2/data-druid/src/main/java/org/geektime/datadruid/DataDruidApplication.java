package org.geektime.datadruid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement(proxyTargetClass = true)
public class DataDruidApplication implements CommandLineRunner {

    @Autowired
    private FooService ser;
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DataDruidApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("4444445555"+dataSource.toString());
        new Thread(() -> ser.getFoo()).start();
        new Thread(() -> ser.getFoo()).start();
    }
}
