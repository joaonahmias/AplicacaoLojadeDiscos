package com.example.lojadediscos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscosDAO {
    private Conexao c;
    private String CD = "INSERT INTO discos (titulo,artista,genero,ano,tipo) VALUES(?,?,?,?,?)";
    private String BD = "SELECT * FROM discos WHERE id = ?";
    private String RLD = "SELECT * FROM discos";
    private String AD = "UPDATE discos SET titulo=?,artista=?,genero=?,ano=?,tipo=? WHERE id=?";
    private String RD = "DELETE FROM discos WHERE id=?";
    public DiscosDAO(){
        c = new Conexao("jdbc:postgresql://localhost:5432/LojadeDiscos", "postgres", "123");
    }

    public void cadastrarDisco(Discos disco){
        try{
            c.conectar();
            PreparedStatement statement = c.getMinhaConexao().prepareStatement(CD);
            statement.setString(1, disco.getTitulo());
            statement.setString(2, disco.getArtista());
            statement.setString(3 , disco.getGenero());
            statement.setInt(4,disco.getAno());
            statement.setString(5,disco.getTipo());
            statement.executeUpdate();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao Cadastrar Disco");
        }
    }

    public Discos BuscarDisco(int id){
        Discos disco=null;
        try{
            c.conectar();
            PreparedStatement statement = c.getMinhaConexao().prepareStatement(BD);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                disco = new Discos(rs.getInt("id"),rs.getString("titulo"), rs.getString("artista"), rs.getString("genero"), rs.getInt("ano"), rs.getString("tipo"));
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao Buscar disco");
        }
        return disco;
    }

    public void atualizarDisco(Discos disco){
        try{
            c.conectar();
            PreparedStatement statement = c.getMinhaConexao().prepareStatement(AD);
            statement.setString(1, disco.getTitulo());
            statement.setString(2, disco.getArtista());
            statement.setString(3 , disco.getGenero());
            statement.setInt(4,disco.getAno());
            statement.setString(5,disco.getTipo());
            statement.setInt(6,disco.getId());
            statement.executeUpdate();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao Atualizar Disco");
        }
    }

    public void removerDisco(int id){
        try{
            c.conectar();
            PreparedStatement statement = c.getMinhaConexao().prepareStatement(RD);
            statement.setInt(1,id);
            statement.executeUpdate();
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro ao Remover Disco");
        }
    }

    public ArrayList <Discos> RelatoriodeDiscos(){
        Discos disco;
        ArrayList <Discos> discos = new ArrayList <Discos>();
        try{
            c.conectar();
            Statement statement = c.getMinhaConexao().createStatement();
            ResultSet rs = statement.executeQuery(RLD);
            while(rs.next()){
                disco = new Discos(rs.getInt("id"),rs.getString("titulo"), rs.getString("artista"), rs.getString("genero"), rs.getInt("ano"), rs.getString("tipo"));
                discos.add(disco);
            }
            c.desconectar();
        }catch(Exception e){
            System.out.println("Erro no Relatório de Discos");
        }
        return discos;
    }
}
