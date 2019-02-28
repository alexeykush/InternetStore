package internetStore.dto;

import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private int id;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        id = hashCode();
    }

    public User(String name, String surname, String login, String password) {
        this(login, password);
        this.name = name;
        this.surname = surname;
        id = hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
