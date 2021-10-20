package com.javasec.learndemo.local.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectField {
    public static void main(String[] args) throws Exception {
        Class testField = ToBeReflect.class;
        Constructor constructor = testField.getDeclaredConstructor();
        ToBeReflect testField1 = (ToBeReflect) constructor.newInstance();
        // 反射获取成员变量
        Field numA = testField.getDeclaredField("numA");
        Field numB = testField.getDeclaredField("numB");
        // 更改权限，不然下面的set会报错
        numA.setAccessible(true);
        numB.setAccessible(true);
        // 使用field.set(obj, value) 来修改值
        numA.set(testField1, 20);
        numB.set(testField1, 20);

        // true
        System.out.println(numA.get(testField1)==numB.get(testField1));
    }
}

class ToBeReflect{
    final Integer numA = 100;
    private Integer numB = 200;
    public ToBeReflect(){ }
}