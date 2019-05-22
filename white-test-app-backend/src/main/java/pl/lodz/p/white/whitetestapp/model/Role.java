package pl.lodz.p.white.whitetestapp.model;

public enum Role {
    MODERATOR, REDACTOR, CANDIDATE;

    public String getAppRole() {
        return "ROLE_" + this.name();
    }
}

