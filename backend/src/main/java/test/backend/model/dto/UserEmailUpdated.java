package test.backend.model.dto;

import lombok.Getter;
import lombok.Setter;
import test.backend.model.entity.UserEntity;

@Getter
@Setter
public class UserEmailUpdated extends AbstractEntityResponseDto {
    private String email;
    private String passwordMd5;

    public static UserEmailUpdated from(UserEntity user) {
        UserEmailUpdated updated = new UserEmailUpdated();
        updated.setEmail(user.getEmail());
        updated.setPasswordMd5(user.getPasswordMd5());
        updated.setUuid(user.getUuid());
        updated.setActive(user.getActive());
        return updated;
    }
}
