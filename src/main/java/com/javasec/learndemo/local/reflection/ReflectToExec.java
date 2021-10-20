package com.javasec.learndemo.local.reflection;

import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectToExec {
    public static void main(String[] args) throws Exception {
        // 不使用反射
        // System.out.println(IOUtils.toString(Runtime.getRuntime().exec("ipconfig").getInputStream(), "UTF-8"));

        // 使用反射
        // 加载类
        Class runtimeExecClass = Class.forName("java.lang.Runtime");
        // 使用getDeclaredConstructor，拿到构造函数
        Constructor constructor = runtimeExecClass.getDeclaredConstructor();
        // 修改访问权限，因为源码中Runtime类的构造方法修饰符为private
        constructor.setAccessible(true);
        // 使用newInstance实例化，相当于Runtime rt = new Runtime();
        Object runtimeExecInstance = constructor.newInstance();
        /* 遍历当前对象拥有的方法
        Method[] methods = runtimeExecClass.getMethods();
        for (Method method : methods){
            System.out.println(method);
        }*/
        // 加载exec方法
        Method runtimeExecMethod = runtimeExecClass.getMethod("exec", String.class);

        // 用invoke去调用exec方法
        Process process  = (Process) runtimeExecMethod.invoke(runtimeExecInstance,"ipconfig");
        // 获取命令执行并打印
        InputStream in = process.getInputStream();
        System.out.println(IOUtils.toString(in, "utf-8"));
    }
}