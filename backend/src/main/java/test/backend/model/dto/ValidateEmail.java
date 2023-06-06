package test.backend.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import test.backend.constants.ValidationConstants;

@Getter
@Setter
public class ValidateEmail {
    @NotNull(message = ValidationConstants.Email.MESSAGE_NOT_NULL)
    @Pattern(regexp = ValidationConstants.Email.REGEX,
            message = ValidationConstants.Email.MESSAGE_INVALID)
    private String email;
}
