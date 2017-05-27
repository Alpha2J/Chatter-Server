package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.dao.impl.BaseDaoImpl;
import org.junit.Test;

/**
 * Created by cn.sourcecodes on 2017/5/24.
 */
public class BatchTest extends BaseDaoImpl<Person> {
    @Test
    public void batchAdd() {
        String sql = "INSERT INTO person(name, nickname) VALUES( ?, ? )";

        Object[][] params = new Object[10][2];

        params[0][0] = "家爵";
        params[0][1] = "alpha";

        params[1][0] = "222345678901";
        params[1][1] = "kaka";

        batch(sql, params);
    }
}
