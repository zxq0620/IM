package com.im.net.server.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetApplicationContext {

    private static class Singletn {
        private static ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:spring.xml", "classpath*:beans.xml" });
    }

    public static ApplicationContext getInstance() {
        return Singletn.context;
    }


}

