package pl.lodz.p.white.whitetestapp.accountmanager.response;

public class PositionResponse {

    private String name;

    private boolean activated;

    public String getName() {
        return name;
    }

    public PositionResponse setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public PositionResponse setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }
}