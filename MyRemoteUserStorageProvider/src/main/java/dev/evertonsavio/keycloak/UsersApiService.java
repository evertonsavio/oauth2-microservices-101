package dev.evertonsavio.keycloak;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersApiService {

    @GET
    @Path("/{userName}")
    User getUserDetails(@PathParam("userName") String userName);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}/verify-password")
    VerifyPasswordResponse verifyUserPassword(@PathParam("userName") String userName, String password);
}
