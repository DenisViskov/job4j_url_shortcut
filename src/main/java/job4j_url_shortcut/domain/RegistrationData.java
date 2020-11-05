package job4j_url_shortcut.domain;

import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 05.11.2020
 */
public class RegistrationData {
    private String login;
    private String password;
    private boolean isRegistered;

    public RegistrationData(String login, String password, boolean isRegistered) {
        this.login = login;
        this.password = password;
        this.isRegistered = isRegistered;
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

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationData that = (RegistrationData) o;
        return isRegistered == that.isRegistered &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, isRegistered);
    }
}
