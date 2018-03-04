package com.tsyrulik.dmitry.model.filter;

import com.tsyrulik.dmitry.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter( urlPatterns = { "/jsp/client/*", "/jsp/trainer/*"},
        initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") ,
                @WebInitParam(name = "ADMIN_MENU", value = "/jsp/admin/admin_page.jsp")})
public class SecurityFilter implements Filter {
    private static final String PARAM_USER = "user";
    private String indexPathGuest;
    private String indexPathAdminMenu;
    public void init(FilterConfig fConfig) throws ServletException {
        indexPathGuest = fConfig.getInitParameter("INDEX_PATH");
        indexPathAdminMenu = fConfig.getInitParameter("ADMIN_PAGE");
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        User user = (User)((HttpServletRequest) request).getSession().getAttribute(PARAM_USER);
        if(user == null){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPathGuest);
            dispatcher.forward(request,response);
        }
        else if(user.getRole().equals("admin")){
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPathAdminMenu);
            dispatcher.forward(request,response);
        }
        else {
            String path = ((HttpServletRequest) request).getRequestURI();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request,response);
        }
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
}