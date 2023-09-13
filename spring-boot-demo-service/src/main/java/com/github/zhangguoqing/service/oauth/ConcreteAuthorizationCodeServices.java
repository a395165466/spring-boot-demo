package com.github.zhangguoqing.service.oauth;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.util.Base64;
import java.util.UUID;

@Component
public class ConcreteAuthorizationCodeServices implements AuthorizationCodeServices {
    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        return null;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String s) throws InvalidGrantException {
        return null;
    }

    public static void main(String[] args) {
//        String base64Str = Base64.getEncoder().encodeToString(a.getBytes());

        System.out.println(UUID.randomUUID());
    }
}
