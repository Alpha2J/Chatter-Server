package cn.sourcecodes.chatterServer.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截所有的请求, 为请求设置编码和contentType
 * Created by cn.sourcecodes on 2017/5/22.
 */
public class EncodingAndContentTypeFilter implements Filter {
    private String encode;
    private String contentType;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;

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

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.encode = config.getInitParameter("encode");
        this.contentType = config.getInitParameter("contentType");
    }

}
