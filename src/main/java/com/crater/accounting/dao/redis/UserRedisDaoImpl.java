package com.crater.accounting.dao.redis;

import com.crater.accounting.bean.database.UserRedisDataPojo;
import com.crater.accounting.dao.UserRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserRedisDaoImpl implements UserRedisDao {

    private RedisTemplate<String, UserRedisDataPojo> userRedisTemplate;

    @Override
    public void updateUser(UserRedisDataPojo userDataPojo) {
        var opsForValue = userRedisTemplate.opsForValue();
        opsForValue.set(userDataPojo.userName(), userDataPojo, 3, TimeUnit.MINUTES);
//        valueOps.set("token:" + tokenPojo.token(), tokenPojo, tokenPojo.timeout(), TimeUnit.MINUTES);
    }

    @Override
    public UserRedisDataPojo getUsers(String userName) {
        var opsForValue = userRedisTemplate.opsForValue();
        return opsForValue.get(userName);
    }

    @Autowired
    public void setUserRedisTemplate(RedisTemplate<String, UserRedisDataPojo> userRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
    }
}
