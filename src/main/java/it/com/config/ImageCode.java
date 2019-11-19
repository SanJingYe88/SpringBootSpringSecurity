package it.com.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ImageCode {

    //图像
    private BufferedImage image;
    //验证码
    private String code;
    //有效期
    private LocalDateTime expireTime;

    /**
     * 生成验证码
     * @param image 图像
     * @param code 验证码
     * @param expireIn 有效期
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
