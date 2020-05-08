package sample;

import java.io.Serializable;

public class Guests implements Serializable {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String star;

    public Guests(String name, String surname, String login, String password, String star) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.star = star;
    }

    public Guests() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
