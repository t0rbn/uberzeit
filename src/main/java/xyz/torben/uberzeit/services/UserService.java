package xyz.torben.uberzeit.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import xyz.torben.uberzeit.entities.UserInfo;

@Service
public class UserService {
    public UserInfo getUserInfo() {
        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserInfo.fromDefaultOidcUser(principal);
    }
}

