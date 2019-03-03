package org.geektime.datadruid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: justd
 * @Date: 19-3-2 20:31
 * @Description:
 */
@Repository
@Slf4j
public class FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void getFoo(){
        Long aLong = jdbcTemplate.queryForObject("select id from foo where id=1 for update", Long.class);
        log.info(aLong.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
