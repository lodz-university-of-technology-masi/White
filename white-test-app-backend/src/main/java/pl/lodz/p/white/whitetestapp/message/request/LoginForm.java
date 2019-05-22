package pl.lodz.p.white.whitetestapp.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    @Size(min=3, max = 60)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginForm setPassword(String password) {
        this.password = password;
        return this;
    }
}
