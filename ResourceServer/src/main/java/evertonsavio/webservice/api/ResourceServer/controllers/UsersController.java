package evertonsavio.webservice.api.ResourceServer.controllers;

import evertonsavio.webservice.api.ResourceServer.data.LockEntity;
import evertonsavio.webservice.api.ResourceServer.data.LocksRepository;
import evertonsavio.webservice.api.ResourceServer.data.UserEntity;
import evertonsavio.webservice.api.ResourceServer.data.UsersRepository;
import evertonsavio.webservice.api.ResourceServer.model.User;
import evertonsavio.webservice.api.ResourceServer.model.UserRest;
import evertonsavio.webservice.api.ResourceServer.services.KeycloakAdminClientService;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    KeycloakAdminClientService kcAdminClient;

    @Autowired
    LocksRepository locksRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/status")
    public String status(){
        return "working";
    }

    @GetMapping("/check")
    public String check() { return "checking"; }

    @GetMapping("/locks")
    public ResponseEntity locks(@AuthenticationPrincipal Jwt jwt){
        String email = (String) jwt.getClaims().get("email");

        UserEntity user = usersRepository.findByEmail(email);
        ArrayList<LockEntity> locks = locksRepository.findByUserRootId(user.getUuid());

        return ResponseEntity.status(HttpStatus.OK).body(locks);
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
