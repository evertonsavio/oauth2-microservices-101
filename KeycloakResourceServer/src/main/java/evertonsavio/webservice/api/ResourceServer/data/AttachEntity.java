package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "attacheddevices")
@Document(collection = "attacheddevices")
public class AttachEntity implements Serializable {

    private static final long serialVersionUID = 8123911498406123243L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "lock_id", nullable = false)
    private LockEntity lock;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "gateway_id", nullable = false)
    private GatewayEntity gateway;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public LockEntity getLock() {
        return lock;
    }

    public void setLock(LockEntity lock) {
        this.lock = lock;
    }

    public GatewayEntity getGateway() {
        return gateway;
    }

    public void setGateway(GatewayEntity gateway) {
        this.gateway = gateway;
    }
}
