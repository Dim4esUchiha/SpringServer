package org.dim4es.springapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should not be empty!")
    private String email;

    @Column(name = "nickname")
    @NotEmpty(message = "Nickname should not be empty!")
    @Size(min = 2, max = 30, message = "Nickname should be between 2 and 30 characters!")
    private String nickname;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty!")
    @Size(min = 3, max = 30, message = "Password should be between 2 and 30 characters!")
    private String password;

    @Column(name = "last_location")
    private String lastLocation;

    public User() {
    }

    public User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }
}
