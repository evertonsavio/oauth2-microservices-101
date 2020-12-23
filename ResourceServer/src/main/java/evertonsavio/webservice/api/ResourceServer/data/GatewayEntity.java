package evertonsavio.webservice.api.ResourceServer.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Entity
//@Table(name = "gateways")
@Document(collection = "gateways")
public class GatewayEntity implements Serializable {

    private static final long serialVersionUID = 1844395341275593379L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String gatewayId;

    @Column(nullable = false)
    private String userRootId;

    @Column(nullable = false)
    private String mac;

    @Column(nullable = false)
    private String pUuid;

    @Column(nullable = false)
    private String gatewayName;

    @Column(nullable = false)
    private String aesKey;

    @Column
    private String type;

    @Column
    private String ticket;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "gateway", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AttachEntity> attachedDevices;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getUserRootId() {
        return userRootId;
    }

    public void setUserRootId(String userRootId) {
        this.userRootId = userRootId;
    }

    public String getMac() { return mac; }

    public void setMac(String mac) { this.mac = mac; }

    public String getpUuid() { return pUuid; }

    public void setpUuid(String pUuid) { this.pUuid = pUuid; }

    public String getGatewayName() { return gatewayName; }

    public void setGatewayName(String gatewayName) { this.gatewayName = gatewayName; }

    public String getAesKey() { return aesKey; }

    public void setAesKey(String aesKey) { this.aesKey = aesKey; }

    public UserEntity getUser() { return user; }

    public void setUser(UserEntity user) { this.user = user; }

    public Set<AttachEntity> getAttachedDevices() {
        return attachedDevices;
    }

    public void setAttachedDevices(Set<AttachEntity> attachedDevices) {
        this.attachedDevices = attachedDevices;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
