package oeg.geektime.transactionpropagation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: justd
 * @Date: 19-3-2 18:34
 * @Description:
 */
@Service
@Slf4j
public class FooServiceImpl implements FooService {

    @Autowired
    public JdbcTemplate jdbcTemple;
    @Autowired
    public FooService fooService;

    @Override
    public void insert() {

    }

    /**
     * 内部事务都不影响外部
     *  REQUIRES_NEW
     *          始终启动一个新事务  两个事物没有关联
     *  NESTED
     *          在事务内启动一个内嵌事务  两个事物有关联
     *          外部事务回滚，内起那时伍也回滚
     *
     */

    /**
     * Propagation.NESTED  内嵌事务失败不影响外面的事务，外部事务照常提交
     * Propagation.REQUIRES_NEW 挂起外部事务，当前新建事务，内部事务失败不影响外部事物
     * <p>
     * Propagation.NESTED
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void insertThenRollback() throws Exception {
        jdbcTemple.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");

        throw new Exception();

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void invokeInsertThenRollback() {
        jdbcTemple.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        try {
            fooService.insertThenRollback();
        } catch (Exception e) {
            log.error("第一层异常", e);
        }
        //throw new RuntimeException();
    }
}
