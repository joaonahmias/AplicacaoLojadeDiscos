//Mudar o nome do package para a sua pasta.
package com.example.lojadediscos;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestesController {
    @RequestMapping(method= RequestMethod.GET, value = "/doContar")
    public void Contador(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //verifica se existe uma sessão, se não existir retorna false e cria a sessão porque o create está true.
        HttpSession sessao = request.getSession(true);
        
        //faz um get no atributo de nome "contador". Se ele não existir é retornado null.
        Integer contador = (Integer) sessao.getAttribute("contador");

        //Se esse sessão foi criada agora é criado o parametro e inicializado com 0.
        if(sessao.isNew()){
            contador=0;
        }
        //Caso o atributo já exista na sessão o contador é incrementado
        else{
            contador++;
        }

        //o atributo é de fato criado ou atualizado na sessão
        sessao.setAttribute("contador",contador);

        //Printo na tela o novo Atributte
        response.getWriter().println(contador);

    }

    @RequestMapping(method= RequestMethod.GET, value = "/doMostrarSessao")
    public void mostraSessao(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession sessao = request.getSession();
        Integer contador = (Integer) sessao.getAttribute("contador");
        if(contador == null){
            contador=0;
        }
        else{
            contador++;
        }

        sessao.setAttribute("contador", contador);

        //Pegando Data de criação da sessão(em milisegundos)
        var dataCriacao = sessao.getCreationTime();

        //Pegando o id da Sessão
        var id = sessao.getId();

        //Pegando quando foi o ultimo acesso(em milisegundos)
        var ultimoacesso = sessao.getLastAccessedTime();
        
        var writer = response.getWriter();

        //Printo na tela as informações coletadas.
        writer.println("Id: "+id+" data/hora da Criação: " + dataCriacao + " Último acesso: " + ultimoacesso + " Contador: "+ contador);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/doCookie")
    public void fazerCookie(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        //Crio um cookie passando seu nome e seu valor(o que ele guarda)
        Cookie c = new Cookie("usuario","nahmias");
        
        //Digo seu tempo de duração(em segundos)
        c.setMaxAge(60);
        
        //Adiciono o cookie na resposta
        response.addCookie(c);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/doCookies")
    public void fazerCookies(HttpServletRequest request, HttpServletResponse response) throws IOException{

        Cookie c = new Cookie("Cookie1","Um");
        Cookie c2 = new Cookie("Cookie2","Dois");
        Cookie c3 = new Cookie("Cookie3","Três");
        Cookie c4 = new Cookie("Cookie4","Quatro");
        Cookie c5 = new Cookie("Cookie5","Cinco");

        c.setMaxAge(30);
        c2.setMaxAge(30);

        response.addCookie(c);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);
        response.addCookie(c5);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/lerCookies")
    public void lerCookies(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        //Crio um array de Cookies e passo para ele todos os cookies presentes na minha request
        Cookie cookies [] = request.getCookies();

        var writer = response.getWriter();
        String nome,valor;
        int i;

        //Irei mostras todos os cookies presentes no meu array(se for null, não existem cookies, então ele não tem por que ser mostrado.)
        if(cookies!=null){

            //Criei um html, mas é opcional
            writer.println("<html><body>");
            writer.println("<h1>Cookies</h1>");
            writer.println("<hr />");

            for(i=0;i<cookies.length;i++){

                //Atribuo a variável nome o nome daquele cookie que está na posição índice do array.
                nome = cookies[i].getName();

                //Atribuo a variável valor o valor contido naquele cookie que está na posição índice do array.
                valor = cookies[i].getValue();

                //Por fim, printo na tela
                writer.println("<p>Nome: "+nome+"</p>");
                writer.println("<p>valor: "+valor+"</p>");
                writer.println("<hr />");
            }
            writer.println("</html></body>");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value ="/teste")
    public void teste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession sessao = request.getSession();
        Boolean logado = (Boolean)sessao.getAttribute("logado");
        if(logado!=null&&logado==true){
            response.getWriter().println("Tá logado");
        }
        else{
            response.getWriter().println("Tá deslogado");
        }
    }

    
    
}
