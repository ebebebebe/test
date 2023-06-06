package test.backend.constants;

public interface ValidationConstants {
    interface EmailSubject {
        int MIN = 2;
        int MAX = 255;
    }

    interface Active {
        String MESSAGE_NOT_NULL = "Active status is required";
    }

    interface Email {
        String REGEX = "^[a-zA-Z]+[\\w.-]*@[a-zA-Z]+[\\w-]*\\.[\\w]{2,4}$";
        String MESSAGE_INVALID = "Invalid email address";
        String MESSAGE_NOT_NULL = "Email address is required";
    }

    interface Password {
        String MESSAGE_NOT_NULL = "Password is required";
        int LENGTH = 32;
        String MESSAGE_SIZE = "MD5 password must be " + LENGTH + " characters";
    }
}
