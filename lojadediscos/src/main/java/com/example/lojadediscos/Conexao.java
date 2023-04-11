package com.example.lojadediscos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private Connection minhaConexao;
    private String caminho;
    private String usuario;
    private String senha;

    public Conexao(String caminho, String usuario, String senha){
        this.caminho = caminho;
        this.usuario = usuario;
        this.senha = senha;
    }

    public void conectar(){
        try{
            Class.forName("org.postgresql.Driver");
            minhaConexao = DriverManager.getConnection( caminho,usuario, senha);
        }catch(Exception e){
            System.out.println("Erro na Conexão");
        }
    }

    public void desconectar(){
        try{
            minhaConexao.close();
        }catch(Exception e){
            System.out.println("Erro ao desconectar");
        }
    }

    public Connection getMinhaConexao(){
        return minhaConexao;
    }
}
