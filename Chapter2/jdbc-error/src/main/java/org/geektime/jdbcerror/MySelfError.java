package org.geektime.jdbcerror;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

import java.util.DuplicateFormatFlagsException;

/**
 * @author: justd
 * @Date: 19-2-28 01:06
 * @Description:
 */
@Slf4j
public class MySelfError extends DuplicateKeyException {

    public MySelfError(String msg) {
        super(msg);
        log.info("xxxxxxxxx");
    }

    public MySelfError(String msg, Throwable cause) {
        super(msg, cause);
        log.info("yyyyyyyyyyyyyy");
    }
}
