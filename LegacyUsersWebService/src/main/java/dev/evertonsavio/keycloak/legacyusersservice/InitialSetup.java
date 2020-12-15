package dev.evertonsavio.keycloak.legacyusersservice;

import javax.transaction.Transactional;

import dev.evertonsavio.keycloak.legacyusersservice.data.UserEntity;
import dev.evertonsavio.keycloak.legacyusersservice.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialSetup {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                1L,
                "qswe3asfba4asr4",
                "Everton Savio",
                "Santos Lucas",
                "test@test.com",
                bCryptPasswordEncoder.encode("123123"),
                "",
                false);

        usersRepository.save(user);
    }
}
