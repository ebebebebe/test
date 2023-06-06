package test.backend.model.dto;

import lombok.Getter;
import lombok.Setter;
import test.backend.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserCreated extends AbstractEntityResponseDto {
    private String email;

    public static UserCreated from(UserEntity user) {
        UserCreated userCreated = new UserCreated();
        userCreated.setUuid(user.getUuid());
        userCreated.setEmail(user.getEmail());
        userCreated.setActive(user.getActive());
        return userCreated;
    }

    public static List<UserCreated> from(List<UserEntity> all) {
        return Optional.ofNullable(all).orElse(new ArrayList<>())
                .stream()
                .map(UserCreated::from)
                .collect(Collectors.toList());
    }
}
