package com.javasec.learndemo.local.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RMIClient {
    public static void main(String[] args) throws NamingException {
        String url = "rmi://localhost:1097/Object";
        //System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(url);
    }
}
