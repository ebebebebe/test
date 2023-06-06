package test.backend.model.dto;

import lombok.Getter;
import lombok.Setter;
import test.backend.model.entity.UserEntity;

@Getter
@Setter
public class UserUpdated extends AbstractEntityResponseDto {
    private String email;

    public static UserUpdated from(UserEntity user) {
        UserUpdated userUpdated = new UserUpdated();
        userUpdated.setUuid(user.getUuid());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setActive(user.getActive());
        return userUpdated;
    }
}
