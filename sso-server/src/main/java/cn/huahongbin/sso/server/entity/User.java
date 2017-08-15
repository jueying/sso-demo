package cn.huahongbin.sso.server.entity;

/**   
 * @ClassName:  User   
 * @Description:用户类
 * @author: jueying 
 * @date:   2017年8月13日 下午9:53:40   
 *     
 */
public class User {
    private long id;
    private String username;
    private String password;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
    
}
