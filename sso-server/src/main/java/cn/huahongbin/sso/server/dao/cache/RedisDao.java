package cn.huahongbin.sso.server.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import cn.huahongbin.sso.server.entity.TokenToURL;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**   
 * @ClassName:  RedisDao   
 * @Description:Redis缓存访问对象   
 * @author: jueying 
 * @date:   2017年8月13日 下午9:58:41   
 */
public class RedisDao {
    private JedisPool jedisPool;
    
    public RedisDao(String host, int port) {
        jedisPool = new JedisPool(host, port);
    }
    
    private RuntimeSchema<TokenToURL> schema = RuntimeSchema.createFrom(TokenToURL.class);
    
    public TokenToURL getTokenToURL(String token) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "token:" +  token;
                //redis内部没有实现序列化操作
                //get -> byte[] -> 反序列化 -> object(ClientURL)
                byte[] bytes = jedis.get(key.getBytes());
                
                if (bytes != null) {
                    //空对象
                    TokenToURL t = schema.newMessage();
                    //填充空对象
                    ProtostuffIOUtil.mergeFrom(bytes, t, schema);
                    return t;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public String putTokenToURL(TokenToURL t) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "token:" +  t.getToken();
                //get -> byte[] -> 反序列化 -> object(clientURL)
                byte[] bytes = ProtostuffIOUtil.toByteArray(t, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;//一小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
        }
        return null;
    }
}
