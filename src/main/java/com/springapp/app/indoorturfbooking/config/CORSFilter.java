package com.springapp.app.indoorturfbooking.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setHeader("Access-Control-Origin","*");
        response1.setHeader("Access-Control-Allow-Credentials","true");
        response1.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,OPTIONS,DELETE");
        response1.setHeader("Access-Control-Max-Age","3600");
        response1.setHeader("Access-Control-Allow-Headers","X-Requested-With,Content-Type,Authorization,Origin,Accept,Access-Control-Request-Method,Access-Control-Request-Headers");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
