package org.geektime.simplejdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author: justd
 * @Date: 19-2-25 23:38
 * @Description:
 */
@Data
@Builder
public class Foo {
    private int id;
    private String bar;
}
