package cn.huahongbin.sso.client2.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**   
 * @ClassName:  LoginFilter   
 * @author: jueying 
 * @date:   2017年8月14日 下午4:37:52   
 *     
 */
public class SSOFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
        String token = request.getParameter("token");
        String username = (String) session.getAttribute("username");
        String logoutURL = URLEncoder.encode("http://localhost:8082/client2/user/logout", "UTF-8");
        
        //判断是否已经登录
        if (null == username) {
            //判断是否在处理token验证逻辑(包含token参数)
            if (null != token && !"".equals(token)) {
                PostMethod postMethod = new PostMethod("http://localhost:8080/server/user/token");
                postMethod.addParameter("token", token);
                postMethod.addParameter("logoutURL", logoutURL); //用于注销所有子系统
                HttpClient httpClient = new HttpClient();
                try {
                    httpClient.executeMethod(postMethod);
                    username = postMethod.getResponseBodyAsString();
                    postMethod.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != username && !"".equals(username)) {
                    session.setAttribute("username", username);
                    chain.doFilter(request, response);
                } else {
                    response.sendRedirect("http://localhost:8080/server/index.jsp?service=" + url);
                }
            } else {
                response.sendRedirect("http://localhost:8080/server/index.jsp?service=" + url);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }

}
