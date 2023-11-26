package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private Integer id;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getIDUsuario() {
        return this.id;
    }

    public String getEmailUsuario() {
        return this.email;
    }

    public String getPasswordUsuario() {
        return this.password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            Usuario usuario = (Usuario)obj;
            return Objects.equals(this.id, usuario.id) && Objects.equals(this.email, usuario.email);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.email, this.password});
    }

    public String toString() {
        return "usuario{id=" + this.id + ", email='" + this.email + '\'' + ", password='" + this.password + '\'' + '}';
    }
}