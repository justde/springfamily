package org.geektime.transactiodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;

/**
 * @author: justd
 * @Date: 19-2-27 22:43
 * @Description:
 */
@Service
public class FooServiceImpl implements FooService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into FOO (BAR) values ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertThenRollback() throws Exception {
        jdbcTemplate.execute("insert into FOO (BAR) values ('BBB')");
        throw new Exception();
    }

    @Override
    public void invertInsertThenRollback() throws Exception {
        fooService.insertThenRollback();
    }
}
