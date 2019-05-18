package pl.lodz.p.white.whitetestapp.accountmanager.dtos;

public class AccountDto {
    private String username;
    private String email;
    private String password;
    private String lang;

    public String getUsername() {
        return username;
    }

    public AccountDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountDto setEmail(String email) {
        this.email = email;
        return this;

    }

    public String getPassword() {
        return password;
    }

    public AccountDto setPassword(String password) {
        this.password = password;
        return this;

    }

    public String getLang() {
        return lang;
    }

    public AccountDto setLang(String lang) {
        this.lang = lang;
        return this;

    }
}
