package evertonsavio.webservice.api.ResourceServer.controllers;

import evertonsavio.webservice.api.ResourceServer.model.User;
import evertonsavio.webservice.api.ResourceServer.model.UserRest;
import evertonsavio.webservice.api.ResourceServer.services.KeycloakAdminClientService;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    KeycloakAdminClientService kcAdminClient;

    @GetMapping("/status")
    public String status(){
        return "working";
    }

    //@Secured("ROLE_developer")
    //@PreAuthorize("hasRole('developer')")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "Deleted user id" + id + "and jwt sub = " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("Savio", "Lucas", "");
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        kcAdminClient.addUser(user);
        return "ok";
    }
}
