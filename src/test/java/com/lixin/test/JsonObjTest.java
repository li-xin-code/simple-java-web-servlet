package com.lixin.test;

import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;
import com.lixin.model.entity.User;

/**
 * @author lixin
 */
public class JsonObjTest {

    public static void main(String[] args) {
        JsonObject object = new JsonObject();
        User user = new User("abc", "abc");
        object.add("user", user);
        System.out.println(object);
        System.out.println(JsonUtils.toJsonString("AAA"));
        byte b = 1;
        System.out.println(JsonUtils.toJsonString(b));
        System.out.println(JsonUtils.toJsonString(1));
        System.out.println(JsonUtils.toJsonString(1L));
        char c = 'c';
        System.out.println(JsonUtils.toJsonString(c));
        System.out.println(JsonUtils.toJsonString(2.2f));
        System.out.println(JsonUtils.toJsonString(2.2D));
        int[] arr = {1, 2};
        System.out.println(JsonUtils.toJsonString(arr));
        int[][] arrA = {arr, arr};
        System.out.println(JsonUtils.toJsonString(arrA));
    }

}
