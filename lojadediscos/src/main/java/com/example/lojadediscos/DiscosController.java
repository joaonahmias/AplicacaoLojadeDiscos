package com.example.lojadediscos;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DiscosController {
    int i;
    Discos disco;
    DiscosDAO discoDAO = new DiscosDAO();
    ArrayList <Discos> discos = new ArrayList <Discos>();

    @RequestMapping(method = RequestMethod.POST, value = "/doCadastrar")
    public void CadastrarDiscos(HttpServletRequest request, HttpServletResponse response) throws IOException{
        disco = new Discos();
        disco.setTitulo(request.getParameter("titulo"));
        disco.setAno(Integer.parseInt(request.getParameter("ano")));
        disco.setArtista(request.getParameter("artista"));
        disco.setGenero(request.getParameter("genero"));
        disco.setTipo(request.getParameter("tipo"));
        discoDAO.cadastrarDisco(disco);
        response.setContentType("text/html");
        var writer = response.getWriter();
        writer.println("<html><body><h1>Cadastro de Discos</h1>");
        writer.println("<hr />");
        writer.println("<p>Titulo: "+disco.getTitulo()+"</p>");
        writer.println("<p>Artista: "+disco.getArtista()+"</p>");
        writer.println("<p>Gênero: "+disco.getGenero()+"</p>");
        writer.println("<p>Ano: "+disco.getAno()+"</p>");
        writer.println("<p>Tipo: "+disco.getTipo()+"</p>");
        writer.println("</body></html>");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doBuscar")
    public void BuscarDisco(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        disco = discoDAO.BuscarDisco(id);
        var writer = response.getWriter();
        writer.println("<html><body><h1>Buscar Disco</h1>");
        writer.println("<hr />");
        if(disco!=null){
            writer.println("<p>Titulo: "+disco.getTitulo()+"</p>");
            writer.println("<p>Artista: "+disco.getArtista()+"</p>");
            writer.println("<p>Gênero: "+disco.getGenero()+"</p>");
            writer.println("<p>Ano: "+disco.getAno()+"</p>");
            writer.println("<p>Tipo: "+disco.getTipo()+"</p>");
        }
        else{
            writer.println("<p>Disco não encontrado.</p>");
        }
        writer.println("</body></html>");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doAtualizar")
    public void AtualizarDisco(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        disco = new Discos();
        disco.setId(Integer.parseInt(request.getParameter("id")));
        disco.setTitulo(request.getParameter("titulo"));
        disco.setArtista(request.getParameter("artista"));
        disco.setGenero(request.getParameter("genero"));
        disco.setAno(Integer.parseInt(request.getParameter("ano")));
        disco.setTipo(request.getParameter("tipo"));
        discoDAO.atualizarDisco(disco);
        response.setContentType("text/html");
        var writer = response.getWriter();
        response.sendRedirect("doListar");

    }

    @RequestMapping(method = RequestMethod.GET, value = "/doDeletar")
    public void DeletarDisco(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        var id = Integer.parseInt(request.getParameter("id"));
        discoDAO.removerDisco(id);
        response.setContentType("text/html");
        response.sendRedirect("/doListar");
        /*RequestDispatcher encaminhar = request.getRequestDispatcher("/doCadastrar");
        encaminhar.forward(request, response);
        Taniro, um forward, só pode ser feito para entre  methods iguais. EX: post, post ou get,get
        */   
    }



    @RequestMapping(method = RequestMethod.GET, value = "/doListar")
    public void listarDisco(HttpServletRequest request, HttpServletResponse response) throws IOException{
        discos = discoDAO.RelatoriodeDiscos();
        response.setContentType("text/html");
        var writer = response.getWriter();
        writer.println("<html><body><h1>Relatório de Discos</h1>");
        for(i=0;i<discos.size();i++){
            writer.println("<hr />");
            writer.println("<p>Titulo: "+discos.get(i).getTitulo()+"</p>");
            writer.println("<p>Artista: "+discos.get(i).getArtista()+"</p>");
            writer.println("<p>Gênero: "+discos.get(i).getGenero()+"</p>");
            writer.println("<p>Ano: "+discos.get(i).getAno()+"</p>");
            writer.println("<p>Tipo: "+discos.get(i).getTipo()+"</p>");
            writer.println("<a href='mostrarPaginaAtualizar?id="+discos.get(i).getId()+"'>Atualizar</a>");
            writer.println("<a href='doDeletar?id="+discos.get(i).getId()+"'>Deletar</a>");

        }
        writer.println("</body></html>");
        

    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostrarPaginaAtualizar")
    public void mostrarPaginaAtualizar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        var id = Integer.parseInt(request.getParameter("id"));
        disco = discoDAO.BuscarDisco(id);
        response.setContentType("text/html");
        var writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Atualizar Disco</h1>");
        writer.println("<hr />");
        writer.println("<form action= 'doAtualizar' method= 'post'>");
        writer.println("<input type = 'hidden' name='id' value= '"+ disco.getId()+"'>"+
        "Titulo: <input type = 'text' name = 'titulo' value= '"+ disco.getTitulo()+"'>"+
        "Artista: <input type = 'text' name= 'artista' value= '"+ disco.getArtista()+"'>"+
        "Gênero: <input type = 'text'  name = 'genero' value= '"+ disco.getGenero()+"'>"+
        "Ano: <input type = 'number'  name = 'ano' value= '"+ disco.getAno()+"'>"+
        "Tipo: <input type = 'text'  name = 'tipo' value= '"+ disco.getTipo()+"'></br>");
        writer.println("</br><button type='submit'>Atualizar</button>");
        writer.println("</form>");
        writer.println("</html></body>");

    }

}
