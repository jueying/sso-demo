package cn.huahongbin.sso.server.dao;

import cn.huahongbin.sso.server.entity.User;

/**   
 * @ClassName:  UserDao   
 * @Description:用户数据访问对象   
 * @author: jueying 
 * @date:   2017年8月13日 下午9:55:33   
 *     
 */
public interface UserDao {
    
    User getById(long id);
    
    User getByName(String username);
    
}
