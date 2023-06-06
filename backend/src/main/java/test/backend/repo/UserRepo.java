package test.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.backend.model.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUuid(UUID uuid);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND (u.uuid <> :excludedUuid OR :excludedUuid IS NULL)")
    Optional<UserEntity> findByEmailWithExcludedUuid(String email, UUID excludedUuid);
}
