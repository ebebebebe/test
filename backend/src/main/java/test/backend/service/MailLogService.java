package test.backend.service;

import test.backend.model.dto.CreateMessageLog;
import test.backend.model.entity.MailLogEntity;

public interface MailLogService {
    MailLogEntity save(CreateMessageLog ok);
}
