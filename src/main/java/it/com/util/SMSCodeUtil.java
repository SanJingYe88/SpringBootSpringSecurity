package it.com.util;

import it.com.sms.SmsCode;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 短信验证码生成工具
 */
public class SMSCodeUtil {

    public static SmsCode createSMSCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code, 60);
    }
}
