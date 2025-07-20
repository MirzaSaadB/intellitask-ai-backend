package com.sadsulsoft.ai_powerd_task_assistance.dto;

public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    // Getter and Setter
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}