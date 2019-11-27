package it.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MyUser implements Serializable {

    private int id;
    //用户名
    private String userName;
    //密码
    private String passWord;
    //判断账户是否未过期，未过期返回true 反之返回false.
    private boolean accountNonExpired;
    //判断账户是否未锁定.
    private boolean accountNonLocked;
    //判断用户凭证是否没过期，即密码是否未过期.
    private boolean credentialsNonExpired;
    //判断用户是否可用.
    private boolean enabled;
    //手机号
    private String mobile;
}
