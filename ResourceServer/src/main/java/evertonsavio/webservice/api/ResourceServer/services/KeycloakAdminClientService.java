package evertonsavio.webservice.api.ResourceServer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import evertonsavio.webservice.api.ResourceServer.configurations.KeyCloakConfig;
import evertonsavio.webservice.api.ResourceServer.model.User;
import evertonsavio.webservice.api.ResourceServer.model.UserDto;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakAdminClientService {

    @RabbitListener(queues = "userActivateOauth")
    public void confirmOauthAccount(Message message) throws Exception {

        byte[] body = message.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        UserDto user = objectMapper.readValue(new String(body), UserDto.class);

        try {
            addUser(user);
        }catch (Exception e){
            e.getLocalizedMessage();
        }

    }

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
        Response response = usersResource.create(kcUser);
        String userId = CreatedResponseUtil.getCreatedId(response);
        System.out.println(userId);

        //UserResource userToDelet = usersResource.get("544cf621-5016-42b2-af3e-e184e82fa08d");
        //userToDelet.remove();

    }

    public void addUser(UserDto user) {

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
        kcUser.setUsername(user.getEmail());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        //kcUser.setId(user.getUuid());
        System.out.println("SAVE");
        Response response = usersResource.create(kcUser);

        String userId = CreatedResponseUtil.getCreatedId(response);
        System.out.println(userId);

        //UserResource userToDelet = usersResource.get("544cf621-5016-42b2-af3e-e184e82fa08d");
        //userToDelet.remove();

    }
}
