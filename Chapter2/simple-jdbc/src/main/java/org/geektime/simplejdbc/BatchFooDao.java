package org.geektime.simplejdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: justd
 * @Date: 19-2-26 00:12
 * @Description:
 */
@Repository
public class BatchFooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert(){
        jdbcTemplate.batchUpdate("insert into foo (bar) values (?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setString(1,"sss-"+i);
                    }

                    @Override
                    public int getBatchSize() {
                        return 3;
                    }
                });

    }
}
