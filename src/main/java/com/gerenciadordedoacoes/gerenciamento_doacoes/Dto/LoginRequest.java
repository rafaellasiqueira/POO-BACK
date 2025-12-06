package com.gerenciadordedoacoes.gerenciamento_doacoes.Dto;

public class LoginRequest {
    private String email;
    private String senha;

    public LoginRequest() {}

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
