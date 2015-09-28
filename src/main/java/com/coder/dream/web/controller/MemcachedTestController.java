package com.coder.dream.web.controller;

import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/28.
 */
@Controller
@RequestMapping(value = "/mem")
public class MemcachedTestController {

    @Resource(name = "mc")
    private MemcachedClient mc;

    @RequestMapping(value = "/test")
    public void test() throws Exception{
        mc.set("hello", 0, "Hello,xmemcached");
        String value = mc.get ( "hello" );
        System.out.println("hello=" + value);

        mc.delete("hello");
        value = mc.get( "hello" );
        System.out.println("hello=" + value);

        User user = new User();
        user.setName("tester");
        user.setSex(1);

        mc.set("user", 0, user);
        User loadedUser = mc.get("user");
        System. out .println("hello=" + loadedUser.getName());

        mc.delete("user");
        loadedUser = mc.get( "user" );
        System.out.println("hello=" + (loadedUser == null ? "" : loadedUser.getName()));

        List<User> users = new ArrayList<User>();
        users.add(user);

        mc.set("users", 0, users);
       List<User> loaded = mc.get("users");
        System. out .println("hello=" + loaded.get(0).getName());

        mc.delete ( "users" );
        loaded = mc.get( "users" );
        System.out.println("hello=" + (loaded == null ? "" : loaded.get(0).getName()));
    }
}
