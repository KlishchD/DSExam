package org.example.Core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RepositoryInterface extends Remote {
    void create(Email email) throws RemoteException;
    Email get(int id) throws RemoteException;
    void update(Email email) throws RemoteException;
    void delete(int id) throws RemoteException;
    List<Email> findAllByTitle(String name) throws RemoteException;
    List<Email> findAllByReceiver(String receiver) throws RemoteException;
}
