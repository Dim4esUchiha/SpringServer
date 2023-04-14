package org.dim4es.springapp.models;

import javax.persistence.*;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_name")
    private String chatName;

    public Chat() {
    }

    public Chat(String chatName) {
        this.chatName = chatName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
