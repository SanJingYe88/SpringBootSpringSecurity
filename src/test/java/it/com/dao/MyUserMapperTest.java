package it.com.dao;

import it.com.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserMapperTest {

    @Autowired
    private MyUserMapper myUserMapper;

    @Test
    public void findByUserName(){
        String userName = "admin";
        MyUser myUser = myUserMapper.findByUserName(userName);
        log.info("{}",myUser);
    }
}
