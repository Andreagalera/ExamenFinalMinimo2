package dsa.eetac.upc.edu.finalexamenminimo2;

public class Usuario {
    String username;
    String mail;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
}
