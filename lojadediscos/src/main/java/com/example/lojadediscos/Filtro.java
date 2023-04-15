//Essa parte não funciona. Descobrir erro.

package com.example.lojadediscos;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/doListar")
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("Passou pelo filtro");

        HttpSession sessao = ((HttpServletRequest) servletRequest).getSession();

        Boolean logado = (Boolean) sessao.getAttribute("logado");

        if (logado != null && logado == true){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse) servletResponse).sendRedirect("Login.html");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
