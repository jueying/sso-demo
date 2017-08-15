package cn.huahongbin.sso.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
 * @ClassName:  LoginFilter   
 * @author: jueying 
 * @date:   2017年8月14日 上午8:46:04   
 *     
 */
public class SSOServerFilter implements Filter {

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String service = request.getParameter("service");
        String token = request.getParameter("token");
        String user = (String) request.getSession().getAttribute("sso");
        String sso_token = (String) request.getSession().getAttribute("sso-token");
        
        //判断是否是token验证
        if (null != token) {
            chain.doFilter(request, response);
        } else {
            //两种情况，1，首次请求sso登录。2，其他子系统验证sso登录。
            if (null != user && null != sso_token) {
                //其他子系统登录情况
                if (null != service) {
                    StringBuilder url = new StringBuilder();
                    url.append(service);
                    if (0 <= service.indexOf("?")) {
                        url.append("&");
                    } else {
                        url.append("?");
                    }
                    url.append("token=").append(sso_token);
                    response.sendRedirect(url.toString());
                } else {
                    response.sendRedirect("/server/index.jsp");
                }
            } else {
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
