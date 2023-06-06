package test.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import test.backend.model.dto.CreateMessageLog;
import test.backend.model.entity.MailLogEntity;
import test.backend.repo.MailLogRepo;
import test.backend.service.MailLogService;

@Service
@Validated
@RequiredArgsConstructor
public class MailLogServiceImpl implements MailLogService {
    private final MailLogRepo repo;
    @Override
    public MailLogEntity save(CreateMessageLog ok) {
        return repo.save(ok.toEntity());
    }
}

