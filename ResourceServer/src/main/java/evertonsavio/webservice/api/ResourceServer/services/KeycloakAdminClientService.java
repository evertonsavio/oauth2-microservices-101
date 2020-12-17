package evertonsavio.webservice.api.ResourceServer.services;

import evertonsavio.webservice.api.ResourceServer.configurations.KeyCloakConfig;
import evertonsavio.webservice.api.ResourceServer.model.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakAdminClientService {

    public void addUser(User user) {
        System.out.println("GETTING INSTANCE");
        UsersResource usersResource = KeyCloakConfig.getInstance().realm("padotec").users();
        System.out.println("CREDENTIALS");
        CredentialRepresentation credential = new CredentialRepresentation();

        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(user.getPassword());
        credential.setTemporary(false);

        System.out.println("PASSWORD" + user.getPassword());
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credential));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        System.out.println("SAVE");
        usersResource.create(kcUser);


    }
}
