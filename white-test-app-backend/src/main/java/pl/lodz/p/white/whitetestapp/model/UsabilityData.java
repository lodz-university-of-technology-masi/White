package pl.lodz.p.white.whitetestapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity(name = "USABILITY_DATA")
@Table(name = "USABILITY_DATA")
public class UsabilityData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "IP")
    private String ip;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "M_ID")
    private Long mId;

    @Column(name = "BROWSER")
    private Browser browser;

    @Column(name = "SAVETIME")
    private Instant savetime;

    @Column(name = "RES_W")
    private Integer resW;

    @Column(name = "RES_H")
    private Integer resH;

    @Column(name = "TIME")
    private Double time;

    @Column(name = "DIST")
    private Double distance;

    @Column(name = "MC")
    private Long mouseClicks;

    public String getIp() {
        return ip;
    }

    public UsabilityData setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UsabilityData setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getmId() {
        return mId;
    }

    public UsabilityData setmId(Long mId) {
        this.mId = mId;
        return this;
    }

    public Browser getBrowser() {
        return browser;
    }

    public UsabilityData setBrowser(Browser browser) {
        this.browser = browser;
        return this;
    }

    public Instant getSavetime() {
        return savetime;
    }

    public UsabilityData setSavetime(Instant savetime) {
        this.savetime = savetime;
        return this;
    }

    public Integer getResW() {
        return resW;
    }

    public UsabilityData setResW(Integer resW) {
        this.resW = resW;
        return this;
    }

    public Integer getResH() {
        return resH;
    }

    public UsabilityData setResH(Integer resH) {
        this.resH = resH;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UsabilityData setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getTime() {
        return time;
    }

    public UsabilityData setTime(Double time) {
        this.time = time;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public UsabilityData setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Long getMouseClicks() {
        return mouseClicks;
    }

    public UsabilityData setMouseClicks(Long mouseClicks) {
        this.mouseClicks = mouseClicks;
        return this;
    }
}
