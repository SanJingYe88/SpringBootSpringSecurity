package it.com.config;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误信息
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
