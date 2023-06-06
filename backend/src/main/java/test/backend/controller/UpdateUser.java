package test.backend.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import test.backend.constants.ValidationConstants;
import test.backend.model.entity.UserEntity;

@Getter
@Setter
public class UpdateUser {

    @NotNull(message = ValidationConstants.Password.MESSAGE_NOT_NULL)
    @Size(min = ValidationConstants.Password.LENGTH,
            max = ValidationConstants.Password.LENGTH,
            message = ValidationConstants.Password.MESSAGE_SIZE)
    private String passwordMd5;

    @NotNull(message = ValidationConstants.Active.MESSAGE_NOT_NULL)
    private Boolean active;

    public void updateEntity(UserEntity user) {
        user.setPasswordMd5(passwordMd5);
        user.setActive(active);
    }
}
