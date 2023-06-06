package test.backend.service.validation;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import test.backend.constants.ValidationConstants;
import test.backend.model.dto.CreateUser;

import java.util.Set;
import java.util.stream.Collectors;


public class UserValidationTest extends ValidationTest {

    @Test
    public void createUserSuccess() {
        CreateUser user = new CreateUser();
        user.setEmail("test@test.tes");
        user.setPasswordMd5("SAMPLEPASSWORDMD5_W/35CHARACTERS");

        Set<ConstraintViolation<CreateUser>> violations = super.validate(user);
        assert(violations).isEmpty();
    }

    @Test
    public void createUserNoEmail() {
        CreateUser user = new CreateUser();
        user.setPasswordMd5("SAMPLEPASSWORDMD5_W/35CHARACTERS");

        Set<ConstraintViolation<CreateUser>> violations = validate(user);
        assert(violations.size() == 1);
        assert(violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining()).contains(ValidationConstants.Email.MESSAGE_NOT_NULL));
    }

    @Test
    public void createUserNoPasswordMd5() {
        CreateUser user = new CreateUser();
        user.setEmail("test@test.tes");

        Set<ConstraintViolation<CreateUser>> violations = validate(user);
        assert(violations.size() == 1);
        assert(violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining()).contains(ValidationConstants.Password.MESSAGE_NOT_NULL));
    }
}
