package evertonsavio.webservice.api.ResourceServer.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//@Entity
//@Table(name = "lockrootusers")
@Document(collection = "lockrootusers")
public class LockEntity implements Serializable {

    private static final long serialVersionUID = -4208159174386902064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String lockId;

    @Column(nullable = false)
    private String userRootId;

    @Column(nullable = false)
    private String lockName;

    @Column(nullable = false)
    private String encryptedAesKey;

    @Column(nullable = false)
    private String encryptedPUuidKey;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String mac;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "lock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DeviceEntity> userdevices;

    @JsonIgnore
    @OneToMany(mappedBy = "lock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AttachEntity> attachedDevices;

     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updated_At = new Date();
    }

    /////////////////////////////////////////////////////////////////////////
    ///////////////////////////GETTERS AND SETTERS///////////////////////////


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLockName() { return lockName; }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public String getEncryptedAesKey() {
        return encryptedAesKey;
    }

    public void setEncryptedAesKey(String encryptedAesKey) {
        this.encryptedAesKey = encryptedAesKey;
    }

    public String getEncryptedPUuidKey() { return encryptedPUuidKey; }

    public void setEncryptedPUuidKey(String encryptedPUuidKey) { this.encryptedPUuidKey = encryptedPUuidKey; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getMac() { return mac; }

    public void setMac(String mac) { this.mac = mac; }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getUserRootId() {
        return userRootId;
    }

    public void setUserRootId(String userRootId) {
        this.userRootId = userRootId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<DeviceEntity> getUserdevices() { return userdevices; }

    public void setUserdevices(Set<DeviceEntity> userdevices) { this.userdevices = userdevices; }

    public Set<AttachEntity> getAttachedDevices() { return attachedDevices; }

    public void setAttachedDevices(Set<AttachEntity> attachedDevices) { this.attachedDevices = attachedDevices; }
}