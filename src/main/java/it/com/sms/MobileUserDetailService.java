package it.com.sms;

import it.com.dao.MyUserMapper;
import it.com.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@Configuration
public class MobileUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserMapper myUserMapper;

    /**
     * 更根据手机号查询
     * @param mobile 手机号
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        log.info("输入的 mobile={}",mobile);
        //从数据库查询该用户
        MyUser myUser = myUserMapper.findByUMobile(mobile);

        if(myUser == null){
            return null;
        }

        return new User(myUser.getUserName(), myUser.getPassWord(), myUser.isEnabled(),
                myUser.isAccountNonExpired(), myUser.isCredentialsNonExpired(),
                myUser.isAccountNonLocked(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}