package cn.sourcecodes.test;

import cn.sourcecodes.chatterServer.servlet.entity.ServerResponse;
import cn.sourcecodes.chatterServer.util.JsonUtils;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.Date;

/**
 * Created by cn.sourcecodes on 2017/5/19.
 */
public class GsonTest {
    @Test
    public void beginTest() {
        //序列化
        Gson gson = new Gson();
        gson.toJson(1);
        gson.toJson("json");
        gson.toJson(new Long(10));
        int[] values = { 1 };
        gson.toJson(values);

        System.out.println(gson.toJson(new Long(10)));

        //还原序列化
        int one = gson.fromJson("1", int.class);
        Integer one1 = gson.fromJson("1", Integer.class);
        Long one2 = gson.fromJson("1", Long.class);
        Boolean falseValue = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String anotherStr = gson.fromJson("[\"abc\"]", String.class);
    }

    @Test
    public void testObject() {
        Person person = new Person("alpha", "alpha2J");
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println(json);

        Person unJson = gson.fromJson(json, Person.class);
        System.out.println(unJson.getName());
    }

    @Test
    public void testValidationResponse() {
        ServerResponse validationResponse = new ServerResponse();
        validationResponse.setAction(1);
        validationResponse.setMsg("登录成功");

        String json = new Gson().toJson(validationResponse, ServerResponse.class);
        System.out.println(json);
    }

    @Test
    public void testDate() {
        Date date = new Date();
        String json = JsonUtils.toJson(date, Date.class);
        System.out.println(json);
    }
}

