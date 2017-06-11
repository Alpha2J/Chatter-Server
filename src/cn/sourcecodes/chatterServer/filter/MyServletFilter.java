package cn.sourcecodes.chatterServer.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截所有的请求, 为请求设置编码和contentType
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class MyServletFilter implements Filter {
    private String encode;
    private String contentType;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();
        //如果是index, 直接放过,不做任何处理, 因为客户端要经过index.jsp才能获得cookie, 从而新建session
        if(path.equals("/index.jsp")) {
            chain.doFilter(req, resp);
        } else if(path.equals("/validation")) {
            handleResponse(response);
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if(session.getAttribute("chatter") == null) {
                //如果等于null, 说明没有登录
            } else {
                //如果已经登录了, 那么跳转
                handleResponse(response);
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.encode = config.getInitParameter("encode");
        this.contentType = config.getInitParameter("contentType");
    }

    /**
     * 设置response 的编码和contentType头部
     * @param response
     */
    private void handleResponse(HttpServletResponse response) {
        //设置编码
        if(this.encode != null) {
            response.setCharacterEncoding(this.encode);
        } else {
            response.setCharacterEncoding("UTF-8");
        }
        //设置http头contentType首部
        if(this.contentType != null) {
            response.setContentType(this.contentType);
        } else {
            response.setContentType("application/json;charset=UTF-8");
        }
    }
}
