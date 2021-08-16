package com.kill.malldemo.util;

import com.alibaba.fastjson.JSON;
import com.kill.malldemo.Pojo.User;
import org.springframework.context.annotation.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringBufferInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author lishen
 * @Date 27/7/21 5:50 pm
 **/
public class BeanUtil {

//    public static Map<String, Object> beanToMap(Object bean) throws Exception
//    {
//        //创建Map集合对象
//        Map<String,Object> map = new HashMap<>();
//        //获取对象字节码信息,不要Object的属性
//        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),Object.class);
//        //获取bean对象中的所有属性
//        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor pd : list) {
//            String key = pd.getName();//获取属性名
//            Object value = pd.getReadMethod().invoke(bean);//调用getter()方法,获取内容
//            map.put(key, value);//增加到map集合当中
//        }
//        return map;
//    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return String.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return String.valueOf(value);
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }

//    public static <T> T mapToBean(Map<String, Object> map,Class<T> classType) throws Exception
//    {
//        //采用反射动态创建对象
//        T obj = classType.newInstance();
//        //获取对象字节码信息,不要Object的属性
//        BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
//        //获取bean对象中的所有属性
//        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor pd : list) {
//            String key = pd.getName();    //获取属性名
//            Object value=map.get(key);  //获取属性值
//            pd.getWriteMethod().invoke(obj, value);//调用属性setter()方法,设置到javabean对象当中
//        }
//        return obj;
//    }

    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static void main(String[] args) throws Exception {
        String result;
        User user = new User();
        user.setPhone("123456");
        user.setPassword("lishen");
        user.setNickname("hello");
        user.setAge(10);
        user.setGender(2);
        result = beanToString(user);

        System.out.println("result is: " + result);

        User one = stringToBean(result, User.class);
        System.out.println(one.getNickname());
    }

}
