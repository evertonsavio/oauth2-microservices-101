package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface DeviceRepository extends MongoRepository<DeviceEntity, Long> {//CrudRepository<DeviceEntity, Long> {

    ArrayList<DeviceEntity> findByUserId(String userPkId);
    DeviceEntity findByUserIdAndLockId(String userId, String lockId);
    //ArrayList<LockEntity> findByUserId(Long userId);

}
