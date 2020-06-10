/*
package com.example;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.entity.Delimei017012015;
import com.example.entity.DetectReturn;
import com.example.mapper.Delimei017012015Mapper;
import com.example.mapper.DetectReturnMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private DetectReturnMapper detectReturnMapper;

    @Autowired
    private Delimei017012015Mapper delimei017012015Mapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDb() {
        List<DetectReturn> list = detectReturnMapper.selectList(new EntityWrapper<DetectReturn>());
        System.out.println(list);
    }

    @Test
    public void testImIe() {
        List<Delimei017012015> list = delimei017012015Mapper.selectList(new EntityWrapper<Delimei017012015>());
        System.out.println(list);
    }

}
*/
