package test.backend.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import test.backend.controller.UpdateUser;
import test.backend.model.dto.CreateUser;
import test.backend.model.dto.UpdateUserEmail;
import test.backend.model.dto.ValidateEmail;
import test.backend.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserEntity createUser(@Valid @NotNull CreateUser createUser);

    void checkEmail(@Valid @NotNull ValidateEmail validateEmail);

    UserEntity updateUser(@NotNull UUID uuid, @Valid UpdateUser updateUser);

    UserEntity updateUserEmail(@NotNull UUID uuid, @NotNull @Valid UpdateUserEmail updateUserEmail);

    List<UserEntity> findAll();

    UserEntity findByUuid(@NotNull UUID uuid);
}
