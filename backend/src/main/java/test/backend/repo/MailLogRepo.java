package test.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.backend.model.entity.MailLogEntity;

public interface MailLogRepo extends JpaRepository<MailLogEntity, Long> {
}
