package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface GatewayRepository extends MongoRepository<GatewayEntity, Long>{//CrudRepository<GatewayEntity, Long> {

    GatewayEntity findByGatewayId(String gatewayId);
    ArrayList<GatewayEntity> findByUserRootId(String userRootId);
    GatewayEntity findByTicket(String ticket);
    GatewayEntity findByMacAndUserRootId(String mac, String userRootId);
    GatewayEntity findByUserRootIdAndGatewayId(String userId, String gatewayId);

}
