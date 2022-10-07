package com.example.seguridadjwt.jwt;

public class JwtResponse {
	
	private String accessToken;
    private String tokenType = "Bearer";
    
    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public JwtResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public JwtResponse setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
