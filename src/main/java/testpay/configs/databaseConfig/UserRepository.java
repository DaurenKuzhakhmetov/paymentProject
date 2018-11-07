package testpay.configs.databaseConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import testpay.model.modelsUser.User;

import java.util.Optional;

/**
 * User repository for CRUD operations.
 */

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}