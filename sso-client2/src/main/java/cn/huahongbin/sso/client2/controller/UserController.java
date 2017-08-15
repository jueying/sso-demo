package cn.huahongbin.sso.client2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @ClassName:  UserController   
 * @author: jueying 
 * @date:   2017年8月14日 下午5:42:03   
 *     
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping(value = {"/info"})
    public ModelAndView info() {
        ModelAndView mav = new ModelAndView("info");
        return mav;
    }
}
