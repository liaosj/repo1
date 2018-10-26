package com.liaosijun.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaosijun.annotation.CacheSelect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheAspect {
    //public static Map<String, Object> cache = new HashMap<String, Object>();
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    private ObjectMapper objectMapper=new ObjectMapper();

    public Object cacheIntercept(ProceedingJoinPoint pjp) {
        //key
        String key;
        //拿到被代理的对象
        Object target = pjp.getTarget();
        //获取方法
        String methodName = pjp.getSignature().getName();
        //判断方法是否被缓存注解标注
        //拿到代理对象的字节码类
        Class<?> targetClass = target.getClass();
        //拿到方法对象
        try {
            Method method = targetClass.getMethod(methodName);
            //判断是否标注了缓存注解
            if (method.isAnnotationPresent(CacheSelect.class)) {
                //判断缓存中是否存在数据
                key = targetClass.getCanonicalName() +"."+ methodName;
                ValueOperations<String, Object> rt = redisTemplate.opsForValue();
                Object res = rt.get(key);
                if (res != null) {
                    System.out.println(res);
                    return null;
                } else {
                    res = method.invoke(target, pjp.getArgs());
                    rt.set(key, objectMapper.writeValueAsString(res));
                    return res;
                }
            } else {
                return method.invoke(target, pjp.getArgs());
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
