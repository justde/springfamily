package oeg.geektime.transactionpropagation;

import lombok.Builder;
import lombok.Data;

/**
 * @author: justd
 * @Date: 19-3-2 19:01
 * @Description:
 */
@Data
@Builder
public class Foo {
    private int id;
    private String bar;
}
