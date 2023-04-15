package com.example.lojadediscos;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.POST, value = "/doLogin")
    public void Logar(HttpServletRequest request, HttpServletResponse response) throws IOException{
       Login l = new Login();
       Login laux;
       LoginDAO lDAO = new LoginDAO();
       l.setUsername(request.getParameter("username"));
       l.setSenha(request.getParameter("senha"));
       laux = lDAO.buscarLogin(l.getUsername());
        if(laux!=null&&laux.getUsername().equals(l.getUsername())&& laux.getSenha().equals(l.getSenha())){
            HttpSession sessao = request.getSession();
            sessao.setAttribute("logado", true);
            response.sendRedirect("Index.html");
        }
        else{
            response.sendRedirect("Login.html");
        }
        
        
    }
}
