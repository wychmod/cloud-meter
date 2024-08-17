package com.wychmod.util;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class SpringBeanUtil {

    /**
     * 复制对象的属性
     *
     * @param source 源对象
     * @param target 目标对象的类
     * @param <T>    目标对象的类型
     * @return 复制后的目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            // 创建目标对象实例
            T t = target.getConstructor().newInstance();
            // 使用BeanUtils工具类复制源对象的属性到目标对象
            BeanUtils.copyProperties(source, t);
            // 返回复制后的目标对象
            return t;
        } catch (Exception e) {
            // 如果发生异常，转换为运行时异常抛出
            throw new RuntimeException(e);
        }
    }


    /**
     * 复制列表对象的属性
     *
     * @param source 源列表对象，包含了需要复制的属性的对象
     * @param target 目标列表对象的类型，指定了复制后对象的类型
     * @return 返回一个新列表，其中包含了复制后的对象
     */
    public static <T>List<T> copyListProperties(List<?> source, Class<T> target) {
        // 使用Java 8的流式API对源列表进行迭代和转换
        // 对源列表中的每个对象，使用copyProperties方法复制属性到目标类型
        // 最后将复制后的对象收集到一个新的列表中并返回
        return source.stream().map(s -> copyProperties(s, target)).collect(java.util.stream.Collectors.toList());
    }


}
