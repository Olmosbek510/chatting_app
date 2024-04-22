package uz.inha.chatting_app.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/mainPage.jsp/"})
public class MainPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object o = request.getSession().getAttribute("currentUser");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(o!=null){
            System.out.println("Entering to filter");
            filterChain.doFilter(request,servletResponse);
        }else {
            response.sendRedirect("/404");
        }
    }
}
