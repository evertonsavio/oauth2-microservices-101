package dev.evertonsavio.keycloak.legacyusersservice.service;

import dev.evertonsavio.keycloak.legacyusersservice.response.UserRest;

public interface UsersService {
   UserRest getUserDetails(String userName, String password);
   UserRest getUserDetails(String userName);
}
