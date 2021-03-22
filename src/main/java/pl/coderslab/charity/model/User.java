package pl.coderslab.charity.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 14)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Transient
    private String password2;

    public User(Long id, @NotEmpty @Email String email, @NotEmpty @Size(min = 6, max = 14) String password, boolean enabled, String password2) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.password2 = password2;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
