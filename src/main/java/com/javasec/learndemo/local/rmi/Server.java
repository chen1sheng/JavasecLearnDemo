package com.javasec.learndemo.local.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.out.println("create remote service....");
        System.setProperty("java.rmi.server.hostname","192.168.0.114");
        // 实例化一个前面定义的WorldClock
        WorldClock clock = new WorldClassService();
        // 将这个服务转换为远程服务接口
        WorldClock skeleton = (WorldClock) UnicastRemoteObject.exportObject(clock,45557);
        System.out.println(skeleton);
        // 注册RMI服务到1099端口
        Registry registry = LocateRegistry.createRegistry(1099);
        // 以服务名clock来注册服务
        registry.bind("clock",skeleton);
    }
}
