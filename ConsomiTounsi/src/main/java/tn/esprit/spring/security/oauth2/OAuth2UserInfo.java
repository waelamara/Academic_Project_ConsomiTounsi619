package tn.esprit.spring.security.oauth2;

import java.util.Map;

import tn.esprit.spring.Model.Sexe;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getFirstName();
    
    public abstract String getLastName();
    
    public abstract String getName();

    public abstract String getEmail();
    
    public abstract String getImageUrl();
}
