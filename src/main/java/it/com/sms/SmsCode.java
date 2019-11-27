package it.com.sms;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 短信验证码对象
 */
public class SmsCode {
    @Setter
    @Getter
    private String code;    //验证码
    @Setter
    @Getter
    private LocalDateTime expireTime;   //过期时间

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
