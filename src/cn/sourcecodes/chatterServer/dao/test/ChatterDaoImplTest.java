package cn.sourcecodes.chatterServer.dao.test;

import cn.sourcecodes.chatterServer.dao.ChatterDao;
import cn.sourcecodes.chatterServer.dao.impl.ChatterDaoImpl;
import cn.sourcecodes.chatterServer.entity.Chatter;
import cn.sourcecodes.chatterServer.entity.ChatterPrivateInfo;
import cn.sourcecodes.chatterServer.service.ChatterService;
import cn.sourcecodes.chatterServer.service.impl.ChatterServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ChatterDaoImpl 的单元测试类
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class ChatterDaoImplTest {
    private ChatterService chatterService;
    private ChatterDao chatterDao;

    @Before
    public void init() {
        this.chatterService = new ChatterServiceImpl();
        this.chatterDao = new ChatterDaoImpl();
    }

    @Test
    public void testAddChatter() {
        Chatter chatter = new Chatter();
        chatter.setAccount("54887894");

        ChatterPrivateInfo chatterPrivate = new ChatterPrivateInfo();
        chatterPrivate.setPassword("46456456");

        int isSuccess = chatterService.register(chatter, chatterPrivate);
        if(isSuccess == 0) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }

    @After
    public void destroy() {
        this.chatterService = null;
        this.chatterDao = null;
    }
}
