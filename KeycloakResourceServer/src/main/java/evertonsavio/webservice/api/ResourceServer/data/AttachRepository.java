package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface AttachRepository extends MongoRepository<AttachEntity, String> { //CrudRepository<AttachEntity, Long> {

    AttachEntity findByLockIdAndGatewayId(String lockPkId, String gatewayPkId);
    ArrayList<AttachEntity> findByUserId(String userId);

}
