package evertonsavio.webservice.api.ResourceServer.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "deviceusers")
@Document(collection = "deviceusers")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeviceEntity implements Serializable {

    private static final long serialVersionUID = 3940909225860180353L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String pUuid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lock_id", nullable = false)
    private LockEntity lock;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getpUuid() { return pUuid; }

    public void setpUuid(String pUuid) { this.pUuid = pUuid; }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LockEntity getLock() {
        return lock;
    }

    public void setLock(LockEntity lock) {
        this.lock = lock;
    }
}
