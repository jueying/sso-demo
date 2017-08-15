package cn.huahongbin.sso.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huahongbin.sso.server.dao.UserDao;
import cn.huahongbin.sso.server.dao.cache.RedisDao;
import cn.huahongbin.sso.server.entity.TokenToURL;
import cn.huahongbin.sso.server.entity.User;

/**   
 * @ClassName:  UserServiceImpl   
 * @author: jueying 
 * @date:   2017年8月14日 上午8:22:48   
 *     
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RedisDao redisDao;

    @Override
    public boolean login(String username, String password) {
        User user = userDao.getByName(username);
        
        if (user != null && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public TokenToURL registerClient(String token, String logoutURL) {
        TokenToURL t = redisDao.getTokenToURL(token);
        
        if (t == null) {
            return null;
        }
        
        t.addLogoutURL(logoutURL);
        redisDao.putTokenToURL(t);
        
        return t;
    }

    @Override
    public void createTokenToURL(String token, String username) {
        TokenToURL t = new TokenToURL();
        t.setToken(token);
        t.setUsername(username);
        
        redisDao.putTokenToURL(t);
    }
}
