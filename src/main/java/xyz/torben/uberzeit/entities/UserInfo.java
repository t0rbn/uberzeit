package xyz.torben.uberzeit.entities;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Objects;

public record UserInfo(String email, String givenName, String familyName) {
    public UserInfo {
        Objects.requireNonNull(email);
    }

    public static UserInfo fromDefaultOidcUser(DefaultOidcUser oidcUser) {
        return new UserInfo(
                (String) oidcUser.getAttributes().get("email"),
                (String) oidcUser.getAttributes().get("given_name"),
                (String) oidcUser.getAttributes().get("family_name")
        );
    }
}
