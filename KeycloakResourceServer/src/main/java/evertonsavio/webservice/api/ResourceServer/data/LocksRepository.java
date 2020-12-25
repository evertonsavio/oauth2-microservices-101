package evertonsavio.webservice.api.ResourceServer.data;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LocksRepository extends CrudRepository<LockEntity, String> {//CrudRepository<LockEntity,Long> {

    ArrayList<LockEntity> findByUserRootId(String userRootId);
    LockEntity findByLockId(String lockId);
    LockEntity findByUserRootIdAndMac(String userId, String mac);
    LockEntity findByUserRootIdAndLockId(String userId, String lockId);

}
