package test.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractEntityResponseDto {
    private UUID uuid;
    private Boolean active;
}
