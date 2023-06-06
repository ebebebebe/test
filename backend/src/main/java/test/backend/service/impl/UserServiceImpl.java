package test.backend.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import test.backend.controller.UpdateUser;
import test.backend.exception.ResourceNotFoundException;
import test.backend.exception.EmailAlreadyInUsedException;
import test.backend.model.dto.CreateUser;
import test.backend.model.dto.NotifyCreateUser;
import test.backend.model.dto.UpdateUserEmail;
import test.backend.model.dto.ValidateEmail;
import test.backend.model.entity.UserEntity;
import test.backend.repo.UserRepo;
import test.backend.service.EmailNotificationService;
import test.backend.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final EmailNotificationService emailNotificationService;

    @Override
    @Transactional
    public UserEntity createUser(CreateUser createUser) {
        validateEmail(createUser.getEmail(), null);
        UserEntity save = userRepo.save(createUser.toEntity());
        emailNotificationService.notifyCreateUser(NotifyCreateUser.from(save));
        return save;
    }
    @Override
    public void checkEmail(ValidateEmail validateEmail) {
        validateEmail(validateEmail.getEmail(), null);
    }

    @Override
    public UserEntity updateUser(UUID uuid, UpdateUser updateUser) {
        UserEntity user = findUserByUuid(uuid);
        updateUser.updateEntity(user);
        return userRepo.save(user);
    }

    @Override
    public UserEntity updateUserEmail(UUID uuid, UpdateUserEmail updateUserEmail) {
        validateEmail(updateUserEmail.getEmail(), uuid);
        UserEntity user = findUserByUuid(uuid);
        user.setEmail(updateUserEmail.getEmail());
        return userRepo.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity findByUuid(UUID uuid) {
        return findUserByUuid(uuid);
    }

    private void validateEmail(@NotNull String email, UUID excludedUuid) {
        Optional<UserEntity> existingUser = userRepo.findByEmailWithExcludedUuid(StringUtils.trim(email), excludedUuid);
        if(existingUser.isPresent()) {
            throw new EmailAlreadyInUsedException("Email is not available");
        }
    }

    private UserEntity findUserByUuid(@NotNull UUID uuid) {
        return userRepo.findByUuid(uuid).orElseThrow(() -> new ResourceNotFoundException("User"));
    }
}
