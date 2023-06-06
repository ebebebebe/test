package test.backend.service.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class ValidationTest {

    private Validator validator;

    @BeforeAll
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected <T extends Object> Set<ConstraintViolation<T>> validate(T obj) {
        return validator.validate(obj);
    }
}
