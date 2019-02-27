package org.geektime.transactiodemo;

/**
 * @author: justd
 * @Date: 19-2-27 22:41
 * @Description:
 */
public interface FooService {

    public void insertRecord();
    public void insertThenRollback() throws Exception;
    public void invertInsertThenRollback() throws Exception;

}
