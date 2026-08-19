package org.example.shop.security;

public class JwtResponse {
    private String token;
    private String email;

    public JwtResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
    public JwtResponse() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
