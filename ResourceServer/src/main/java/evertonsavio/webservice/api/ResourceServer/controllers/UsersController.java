package evertonsavio.webservice.api.ResourceServer.controllers;

import evertonsavio.webservice.api.ResourceServer.model.User;
import evertonsavio.webservice.api.ResourceServer.services.KeycloakAdminClientService;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status")
    public String status(){
        return "working";
    }

    @Autowired
    KeycloakAdminClientService kcAdminClient;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        kcAdminClient.addUser(user);
        return "ok";
    }
}
