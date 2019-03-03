package oeg.geektime.transactionpropagation;

/**
 * @author: justd
 * @Date: 19-3-2 18:33
 * @Description:
 */
public interface FooService {

    public void insert();

    public void insertThenRollback() throws Exception;

    public void invokeInsertThenRollback();
}
