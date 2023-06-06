package test.backend.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import test.backend.constants.ValidationConstants;
import test.backend.model.entity.UserEntity;

@Getter
@Setter
public class NotifyCreateUser {
    @NotNull
    @Pattern(regexp = ValidationConstants.Email.REGEX)
    private String email;

    public static NotifyCreateUser from(UserEntity user) {
        NotifyCreateUser dto = new NotifyCreateUser();
        dto.setEmail(user.getEmail());
        return dto;
    }
}
