package com.example.lojadediscos;

class Login{

    private String username;
    private String senha;

    public Login(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}