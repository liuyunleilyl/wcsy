package com.example.common.propertyutils;

import com.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: liuyl
 * @Date: 2020/6/13 20:14
 * @Version: 1.0
 * @Description:读取配置文件内容(有问题)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:application-app.yml")
@SpringBootTest(classes = Application.class)
public class ReadProperty {
    @Value("${mineServer.port}")
    private String serverPort;

    @Test
    public void testOne(){
        System.out.println("serverPort======"+serverPort);
    }
}
