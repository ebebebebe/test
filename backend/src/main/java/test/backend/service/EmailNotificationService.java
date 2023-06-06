package test.backend.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import test.backend.model.dto.NotifyCreateUser;

public interface EmailNotificationService {
    void notifyCreateUser(@NotNull @Valid NotifyCreateUser user);
}
