package com.gr.interceptor.shopadmin;

import com.gr.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-23 00:18
 */
public class OrderInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object personInfoSession = request.getSession().getAttribute("personInfoSession");
        if (personInfoSession != null) {
            PersonInfo personInfo = (PersonInfo) personInfoSession;
            if (personInfo != null
                    && personInfo.getUserId() != null
                    && personInfo.getUserId() > 0
                    && personInfo.getEnableStatus() == 1) {
                return true;
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window,open('" + request.getContextPath() + "/user/login')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
