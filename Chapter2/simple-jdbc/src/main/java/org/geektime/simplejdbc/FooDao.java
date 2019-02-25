package org.geektime.simplejdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: justd
 * @Date: 19-2-25 23:39
 * @Description:
 */
@Slf4j
@Repository
public class FooDao {
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insert() {
        Arrays.asList("a", "b").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)", bar);
        });

        HashMap row = new HashMap();
        row.put("BAR", "TT");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("id of tt :{}", id);
    }

    public void list() {
        Long count = jdbcTemplate.queryForObject("select count (*) from FOO", Long.class);
        log.info("count:{}", count);

        List<String> string = jdbcTemplate.queryForList("select BAR from FOO", String.class);
        string.forEach(a -> log.info("bar的值：{}", a));

        List<Foo> ob = jdbcTemplate.query("select * From FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .bar(resultSet.getString(2)).id(resultSet.getInt(1)).build();
            }
        });
        ob.forEach(a -> log.info("duixiang:{}", a));
    }
}
