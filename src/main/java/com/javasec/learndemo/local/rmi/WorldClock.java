package com.javasec.learndemo.local.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface WorldClock extends Remote {
    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;

    void exec(Object genPayload) throws Exception;
    void execString(String genPayload) throws Exception;
    Object hackClient() throws Exception;
}
