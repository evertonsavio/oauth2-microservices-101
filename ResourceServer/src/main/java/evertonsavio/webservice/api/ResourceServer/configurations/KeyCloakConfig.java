package evertonsavio.webservice.api.ResourceServer.configurations;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://172.31.1.101:8080/auth";
    public final static String realm = "master";
    final static String clientId = "admin-cli";
    final static String clientSecret = "7da668ec-8598-49fb-a542-900d9eedb43c";
    final static String userName = "root";
    final static String password = "Kc@PADOtec!";

    public KeyCloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();

        }
        System.out.println(keycloak.serverInfo().getInfo());

        return keycloak;
    }


}
