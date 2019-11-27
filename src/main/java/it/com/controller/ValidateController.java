package it.com.controller;

import it.com.config.ImageCode;
import it.com.sms.SmsCode;
import it.com.util.ImageCodeUtil;
import it.com.util.SMSCodeUtil;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ValidateController {

    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    public final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = ImageCodeUtil.createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE_CODE, imageCode);
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
    }

    /**
     * 生成短信验证码
     * @param request
     * @param response
     * @param mobile
     * @throws IOException
     */
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) throws IOException {
        SmsCode smsCode = SMSCodeUtil.createSMSCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE + mobile, smsCode);
        // 输出验证码到控制台代替短信发送服务
        System.out.println("您的登录验证码为：" + smsCode.getCode() + ", 有效时间为60秒");
    }
}
