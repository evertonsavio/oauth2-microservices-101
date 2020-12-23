package evertonsavio.webservice.api.ResourceServer.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Entity
//@Table(name = "usersms")
@Document(collection = "usersms")
public class UserEntity implements Serializable {
    
    private static final long serialVersionUID = 2133469464533703956L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String uuid;
    
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LockEntity> locks;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GatewayEntity> gateways;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DeviceEntity> userdevices;

    @Column
    private String ticket;

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicket() { return ticket; }

    public void setTicket(String ticket) { this.ticket = ticket; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public Set<LockEntity> getLocks() { return locks; }

    public void setLocks(Set<LockEntity> locks) { this.locks = locks; }

    public Set<GatewayEntity> getGateways() { return gateways; }

    public void setGateways(Set<GatewayEntity> gateways) { this.gateways = gateways; }

    public Set<DeviceEntity> getUserdevices() {
        return userdevices;
    }

    public void setUserdevices(Set<DeviceEntity> userdevices) {
        this.userdevices = userdevices;
    }
}
