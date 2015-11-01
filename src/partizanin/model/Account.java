package partizanin.model;


import javafx.beans.property.*;

/**
 * Created with Intellij IDEA.
 * Project name: VKproject.
 * Date: 18.10.2015.
 * Time: 16:37.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */

public class Account {

    private  StringProperty secondLogin;
    private StringProperty login;
    private StringProperty password;
    private IntegerProperty id;
    private BooleanProperty used;
    private BooleanProperty active;

    public Account() {
    }

    public Account(String login, String password, boolean used, boolean active, String secondLogin, int id) {
        this.secondLogin = new SimpleStringProperty(secondLogin);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.id = new SimpleIntegerProperty(id);
        this.used = new SimpleBooleanProperty(used);
        this.active = new SimpleBooleanProperty(active);
    }

    public StringProperty getSecondLogin() {
        return secondLogin;
    }

    public void setSecondLogin(String secondLogin) {
        this.secondLogin = new SimpleStringProperty(secondLogin);
    }

    public StringProperty getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = new SimpleStringProperty(login);
    }

    public StringProperty getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);;
    }

    public BooleanProperty getActive() {
        return active;
    }

    public void setActive(Boolean active) {

        this.active = new SimpleBooleanProperty(active);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = new SimpleIntegerProperty(id);
    }

    public void setUsed(Boolean used) {
        this.used = new SimpleBooleanProperty(used);
    }

    public BooleanProperty getUsed() {
        return used;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Account{");
        sb.append("secondLogin='").append(secondLogin).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", id=").append(id);
        sb.append(", used=").append(used);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

}

