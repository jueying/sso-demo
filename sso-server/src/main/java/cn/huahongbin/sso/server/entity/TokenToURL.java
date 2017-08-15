package cn.huahongbin.sso.server.entity;

import java.util.HashSet;
import java.util.Set;

public class TokenToURL {
    private String token;
    
    private String username;
    
    private Set<String> logoutURLs = new HashSet<>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getLogoutURLs() {
        return logoutURLs;
    }

    public void setLogoutURLs(Set<String> logoutURLs) {
        this.logoutURLs = logoutURLs;
    }

    public void addLogoutURL(String logoutURL) {
        this.logoutURLs.add(logoutURL);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
