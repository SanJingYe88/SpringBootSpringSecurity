package it.com.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SmsAuthenticationProvider implements AuthenticationProvider {

    private MobileUserDetailService mobileUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;
        //根据手机号查询
        UserDetails userDetails = mobileUserDetailService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails == null){
            throw new InternalAuthenticationServiceException("未找到与该手机号对应的用户");
        }
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public MobileUserDetailService getUserDetailService() {
        return mobileUserDetailService;
    }

    public void setUserDetailService(MobileUserDetailService mobileUserDetailService) {
        this.mobileUserDetailService = mobileUserDetailService;
    }
}
