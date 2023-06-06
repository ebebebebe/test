package test.backend.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import test.backend.constants.ValidationConstants;
import test.backend.model.entity.UserEntity;

@Getter
@Setter
public class CreateUser {
    @NotNull(message = ValidationConstants.Email.MESSAGE_NOT_NULL)
    @Pattern(regexp = ValidationConstants.Email.REGEX,
            message = ValidationConstants.Email.MESSAGE_INVALID)
    private String email;

    @NotNull(message = ValidationConstants.Password.MESSAGE_NOT_NULL)
    @Size(min = ValidationConstants.Password.LENGTH,
            max = ValidationConstants.Password.LENGTH,
            message = ValidationConstants.Password.MESSAGE_SIZE)
    private String passwordMd5;

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setEmail(email);
        entity.setPasswordMd5(passwordMd5);
        return entity;
    }
}
