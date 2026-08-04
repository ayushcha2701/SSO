package APP.SSO.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import APP.SSO.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByWorkEmailId(String workEmailId);

}
