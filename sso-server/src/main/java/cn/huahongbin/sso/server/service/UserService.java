package cn.huahongbin.sso.server.service;

import cn.huahongbin.sso.server.entity.TokenToURL;

/**   
 * @ClassName:  UserService   
 * @author: jueying 
 * @date:   2017年8月14日 上午8:20:55   
 *     
 */
public interface UserService {

    public boolean login(String username, String password);
    
    public void createTokenToURL(String token, String username);
    
    public TokenToURL registerClient(String token, String logoutURL);
}
