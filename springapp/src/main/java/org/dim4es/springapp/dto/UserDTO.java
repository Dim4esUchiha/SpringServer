package org.dim4es.springapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @Email
    @NotEmpty(message = "Email should not be empty!")
    private String email;

    @NotEmpty(message = "Nickname should not be empty!")
    @Size(min = 2, max = 30, message = "Nickname should be between 2 and 30 characters!")
    private String nickname;

    @NotEmpty(message = "Password should not be empty!")
    @Size(min = 3, max = 30, message = "Password should be between 2 and 30 characters!")
    private String password;

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
}
