package io.swen90007sm2.core.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.swen90007sm2.core.constant.StatusCodeEnume;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * A util class for responding RESTful JSON data
 *
 * @author xiaotian
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final String MSG = "msg";
    public static final String CODE = "code";
    public static final String DATA = "data";

    public R() {
        put(CODE, StatusCodeEnume.SUCCESS.getCode());
        put(MSG, StatusCodeEnume.SUCCESS.getMessage());
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Unknown exception");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put(CODE, code);
        r.put(MSG, msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put(MSG, msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {

        return (Integer) this.get(CODE);
    }

    public R setData(Object data) {
        put(DATA, data);
        return this;
    }

    /**
     * parse the `data` key from the R map with designated type.
     * e.g. if you put("data", new Person()), use this method like getData(new TypeReference<MemberAddressVo>(Person) {});
     *
     * @param <T>
     */
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get(DATA);
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    /**
     * parse the any key from the R map with designated type.
     * e.g. if you put("person", new Person()), use this method like getData("person",new TypeReference<Person>() {});
     *
     * @param <T>
     */
    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }
}
