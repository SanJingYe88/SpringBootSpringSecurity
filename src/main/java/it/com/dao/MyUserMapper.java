package it.com.dao;

import it.com.entity.MyUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserMapper {

    @Select("select * from myuser where user_name = #{userName}")
    MyUser findByUserName(String userName);

    @Select("select * from myuser where mobile = #{mobile}")
    MyUser findByUMobile(String mobile);
}
