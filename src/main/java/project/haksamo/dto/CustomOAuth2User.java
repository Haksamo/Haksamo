package project.haksamo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import project.haksamo.entity.user.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {


    private final User user;

    public CustomOAuth2User(User user){
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public User.Provider getProvider(){
        return user.getProvider();
    }

}
