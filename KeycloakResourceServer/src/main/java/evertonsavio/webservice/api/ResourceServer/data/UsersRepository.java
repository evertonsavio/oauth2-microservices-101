package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<UserEntity, String> {//CrudRepository<UserEntity, Long> {

    UserEntity findByUuid(String uuid);
    UserEntity findByEmail(String email);
    UserEntity findByTicket(String ticket);

}
