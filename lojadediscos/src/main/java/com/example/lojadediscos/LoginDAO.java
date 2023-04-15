package com.example.lojadediscos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

class LoginDAO{
    
    private Conexao c;
    private String CL = "INSERT INTO login VALUES (?,?)";
    private String BL = "SELECT * FROM login WHERE username = ?";
    private String DL = "DELETE FROM login WHERE username = ?";
    private String AL = "UPDATE login SET senha = ? WHERE username = ?";

    public LoginDAO(){
        c = new Conexao("jdbc:postgresql://localhost:5432/LojadeDiscos", "postgres", "123");

    }

    public void criarLogin(Login l){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getMinhaConexao().prepareStatement(CL);
            instrucao.setString(1,l.getUsername());
            instrucao.setString(2, l.getSenha());
            instrucao.executeUpdate();
            c.desconectar();
        }
        catch(Exception e){
            System.out.println("Erro ao Criar Login");
        }

    }

    public Login buscarLogin(String username){
        Login l = null;
        try{
            c.conectar();
            PreparedStatement instrucao = c.getMinhaConexao().prepareStatement(BL);
            instrucao.setString(1,username);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                l = new Login(rs.getString("username"), rs.getString("senha"));
            }
            c.desconectar();
        }
        catch(Exception e){
            System.out.println("Erro ao Buscar Login");
        }
        return l;
    }

    public void DeletarLogin(String username){
        try{
            c.conectar();
            PreparedStatement instrucao = c.getMinhaConexao().prepareStatement(DL);
            instrucao.setString(1,username);
            instrucao.executeUpdate();
            c.desconectar();
        }
        catch(Exception e){
            System.out.println("Erro ao Deletar Login");
        }
    }

    public void atualizarLogin(Login l){
        
        try{
            c.conectar();
            PreparedStatement instrucao = c.getMinhaConexao().prepareStatement(AL);
            instrucao.setString(1, l.getSenha());
            instrucao.setString(2,l.getUsername());
            instrucao.executeUpdate();
            c.desconectar();
        }
        catch(Exception e){
            System.out.println("Erro ao Alterar Login");
        }

    }

}