package cn.huahongbin.sso.server.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huahongbin.sso.server.entity.TokenToURL;
import cn.huahongbin.sso.server.entity.User;
import cn.huahongbin.sso.server.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    public void login(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String service = request.getParameter("service");
        
        //登录成功时
        if (userService.login(user.getUsername(), user.getPassword())) {
            String token = UUID.randomUUID().toString();
            session.setAttribute("sso", user.getUsername());
            session.setAttribute("sso-token", token);
            
            userService.createTokenToURL(token, user.getUsername());
            
            if (null != service) {
                StringBuilder url = new StringBuilder();
                url.append(service);
                if (0 <= service.indexOf("?")) {
                    url.append("&");
                } else {
                    url.append("?");
                }
                url.append("token=").append(token);
                response.sendRedirect(url.toString());
            } else {
                response.sendRedirect("/server/index.jsp");
            }
        } else {
            //登录失败时
            response.sendRedirect("/server/index.jsp?service=" + service);
        }
    }
    
    @RequestMapping(value = {"/token"})
    @ResponseBody
    public String verifyToken(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
        String token = req.getParameter("token");
        String logoutURL = req.getParameter("logoutURL");
        
        TokenToURL t = userService.registerClient(token, logoutURL);
        
        if (t != null && t.getUsername() != null) {
            return t.getUsername();
        }
        
        return null;
    }

}
