package org.example.Socket;

import org.example.Core.Email;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            ClientRepository repository = new ClientRepository("localhost", 8080);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("(0) to exit");
                System.out.println("(1) to create email");
                System.out.println("(2) to get email");
                System.out.println("(3) to update email");
                System.out.println("(4) to delete email");
                System.out.println("(5) to find all emails by title");
                System.out.println("(6) to find all emails by receiver");
                int operation = scanner.nextInt();
                switch (operation) {
                    case 0: return;
                    case 1: {
                        Email email = new Email();
                        System.out.println("Type title:");
                        email.setTitle(scanner.nextLine());
                        email.setTitle(scanner.nextLine());
                        System.out.println("Type text:");
                        email.setText(scanner.nextLine());
                        System.out.println("Type sender:");
                        email.setSender(scanner.nextLine());
                        System.out.println("Type receiver:");
                        email.setReceiver(scanner.nextLine());

                        repository.create(email);
                        break;
                    }
                    case 2: {
                        System.out.println("Type id:");
                        int id = scanner.nextInt();
                        System.out.println(repository.get(id));
                        break;
                    }
                    case 3: {
                        Email email = new Email();
                        System.out.println("Type id:");
                        email.setId(scanner.nextInt());
                        System.out.println("Type title:");
                        email.setTitle(scanner.nextLine());
                        email.setTitle(scanner.nextLine());
                        System.out.println("Type text:");
                        email.setText(scanner.nextLine());
                        System.out.println("Type sender:");
                        email.setSender(scanner.nextLine());
                        System.out.println("Type receiver:");
                        email.setReceiver(scanner.nextLine());

                        repository.update(email);
                        break;
                    }
                    case 4: {
                        System.out.println("Type id:");
                        int id = scanner.nextInt();
                        repository.delete(id);
                        break;
                    }
                    case 5: {
                        System.out.println("Type title:");
                        String title = scanner.nextLine();
                        title = scanner.nextLine();
                        System.out.println(repository.findAllByTitle(title));
                        break;
                    }
                    case 6: {
                        System.out.println("Type receiver:");
                        String receiver = scanner.nextLine();
                        receiver = scanner.nextLine();
                        System.out.println(repository.findAllByReceiver(receiver));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
