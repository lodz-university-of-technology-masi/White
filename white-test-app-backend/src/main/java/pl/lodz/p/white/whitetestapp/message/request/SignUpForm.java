package pl.lodz.p.white.whitetestapp.message.request;

import pl.lodz.p.white.whitetestapp.model.Lang;
import pl.lodz.p.white.whitetestapp.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private Role role;

    private Lang lang;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public Lang getLang() {
        return lang;
    }

    public SignUpForm setLang(Lang lang) {
        this.lang = lang;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public SignUpForm setRole(Role role) {
        this.role = role;
        return this;
    }
}
