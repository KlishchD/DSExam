package org.example.RMI;

import org.example.Core.Repository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException {
        Repository repository = new Repository();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("repository", repository);

        while (true) {
            System.out.println("Working");
        }
    }
}
