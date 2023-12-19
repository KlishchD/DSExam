package org.example.Core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Email implements Serializable {
    private int id;
    private String title;
    private String text;
    private String sender;
    private String receiver;

    public Email() {

    }
    public Email(int id, String title, String text, String sender, String receiver) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}