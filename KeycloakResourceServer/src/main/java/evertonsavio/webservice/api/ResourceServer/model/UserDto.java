package evertonsavio.webservice.api.ResourceServer.model;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 7347445413106122280L;

    private String uuid;
    private String firstName;
    private String lastName;
    private String preferredUserName;
    private String email;
    private String password;
    private String encryptedPassword;
    private Date created_At;
    private Date updated_At;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////////


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

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPreferredUserName() { return preferredUserName; }

    public void setPreferredUserName(String preferredUserName) { this.preferredUserName = preferredUserName; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
