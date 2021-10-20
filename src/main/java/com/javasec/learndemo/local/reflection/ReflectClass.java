package com.javasec.learndemo.local.reflection;

import java.lang.reflect.*;

public class ReflectClass {

    public static void main(String[] args) {
        try {

            //Class获取类的方法一:通过类的实例对象加载;
            User testObject = new User("zhangshan", 19);
            Class Method1Class = testObject.getClass();

            //Class获取类的方法二:类的.class(最安全/性能最好)属性;有点类似python的getattr()。java中每个类型都有class 属性.
            Class Method2Class = User.class;

            //Class对象的获取方法三:运用Class.forName(String className)动态加载类,className需要是类的全限定名(最常用).
            //这种方法也最容易理解，通过类名(jar包中的完整namespace)就可以调用其中的方法，也最符合我们需要的使用场景.
            //j2eeScan burp 插件就使用了这种反射机制。
            String path = "com.sf.isic.User";
            Class Method3Class = Class.forName(path);

            //class对象获取方法四：通过ClassLoader对象的loadClass()方法
            Class Method4class = ClassLoader.getSystemClassLoader().loadClass(path);

            /* 对于一个任意的可以访问到的类，我们都能够通过下面这些方法来知道它的所有的方法和属性；
             * 知道了它的方法和属性，就可以调用这些方法和属性。
             */
            Method[] methods = Method3Class.getMethods();
            //Field[] fields = Method3Class.getFields();
            //Method[] methods = Method2Class.getMethods();
            //Method[] methods = Method1Class.getMethods();

            // 使用默认无参构造函数，直接调用newInstance进行实例化对象
            User user = (User) Method3Class.newInstance();
            // 获取getName方法
            Method methodGetName = Method3Class.getMethod("getName");
            // 用获取到的方法，使用invoke进行调用，参数依次是对象、参数
            Object result = methodGetName.invoke(user);//user.getName();
            //Object result = methodGetName.invoke(new User());
            //new关键字能调用任何构造方法,newInstance()只能调用无参构造方法。但反射的场景中是不应该有机会使用new关键词的。
            System.out.println("x" + result);

            // 使用带参数的构造方法时，需要先从类中加载构造方法
            Constructor constructorMethod = Method3Class.getConstructor(String.class, Integer.class);
            // user1 调用了自定义的构造方法，并且传入了参数进行实例化
            User user1 = (User) constructorMethod.newInstance("eason", 88);
            // 加载setAge方法并使用invoke进行调用
            Method methodSetAge = Method3Class.getMethod("setAge", Integer.class);
            Object setAgeResult = methodSetAge.invoke(user1, 999);//第一个参数是类的对象。第二参数是函数的参数
            //user1.setAge(0000);
            System.out.println(user1.getName() + " is " + user1.getAge() + " years old!");

            Method methodPrint = Method1Class.getMethod("print", String.class, Integer.class);
            Object print = methodPrint.invoke(user1, "eason", 99);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

class User {
    private Integer age;
    private String name;

    public User() {
        this.name = "default name";
    }

    public User(String name, Integer age) { //构造函数，初始化时执行
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(String name, Integer age) {
        System.out.println(name + " is " + age + " years old!");
    }
}