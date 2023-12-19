package org.example.Socket;

import org.example.Core.Email;
import org.example.Core.RepositoryInterface;
import org.example.Utils.Serialization;

import java.awt.event.ContainerEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements RepositoryInterface {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    ClientRepository(String host, int port) throws IOException {
        socket = new Socket(host, port);

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void create(Email email) {
        try {
            out.writeInt(1);

            String bytes = Serialization.toString(email);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Email get(int id) {
        try {
            out.writeInt(2);
            out.writeInt(id);

            int length = in.readInt();
            byte[] bytes = new byte[length];
            in.read(bytes);

            return (Email) Serialization.fromBytes(bytes);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Email email) {
        try {
            out.writeInt(3);

            String bytes = Serialization.toString(email);
            out.writeInt(bytes.length());
            out.writeBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            out.writeInt(4);
            out.writeInt(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Email> findAllByTitle(String title) throws RemoteException {
        try {
            out.writeInt(5);

            String outBytes = Serialization.toString(title);
            out.writeInt(title.length());
            out.writeBytes(outBytes);

            List<Email> emails = new ArrayList<>();
            int count = in.readInt();
            for (int i = 0; i < count; ++i) {
                int length = in.readInt();
                byte[] bytes = new byte[length];
                in.read(bytes);

                emails.add((Email) Serialization.fromBytes(bytes));
            }

            return emails;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Email> findAllByReceiver(String receiver) throws RemoteException {
        try {
            out.writeInt(6);

            String outBytes = Serialization.toString(receiver);
            out.writeInt(receiver.length());
            out.writeBytes(outBytes);

            List<Email> emails = new ArrayList<>();
            int count = in.readInt();
            for (int i = 0; i < count; ++i) {
                int length = in.readInt();
                byte[] bytes = new byte[length];
                in.read(bytes);

                emails.add((Email) Serialization.fromBytes(bytes));
            }

            return emails;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
