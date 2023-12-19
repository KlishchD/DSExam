package org.example.Core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository extends UnicastRemoteObject implements RepositoryInterface {
    private final List<Email> list = new ArrayList<>();
    private int maxId = 0;

    public Repository() throws RemoteException {

    }

    @Override
    public synchronized void create(Email email) throws RemoteException {
        email.setId(maxId);
        maxId++;

        list.add(email);
    }

    @Override
    public synchronized Email get(int id) throws RemoteException {
        Email result = null;
        for (Email email: list) {
            if (email.getId() == id) {
                result = email;
            }
        }
        return result;
    }

    @Override
    public synchronized void update(Email email) throws RemoteException {
        if (list.removeIf(x -> x.getId() == email.getId())) {
            list.add(email);
        }
    }

    @Override
    public synchronized void delete(int id) throws RemoteException {
        list.removeIf(email -> email.getId() == id);
    }

    @Override
    public List<Email> findAllByTitle(String title) throws RemoteException {
        return list.stream().filter(x -> x.getTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public List<Email> findAllByReceiver(String receiver) throws RemoteException {
        return list.stream().filter(x -> x.getReceiver().contains(receiver)).collect(Collectors.toList());
    }
}
