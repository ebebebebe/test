package test.backend.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import test.backend.constants.ValidationConstants;
import test.backend.model.entity.MailLogEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateMessageLog {
    @Valid
    private List<Recipient> recipients;

    @NotNull
    @Pattern(regexp = ValidationConstants.Email.REGEX)
    private String from;

    @NotNull
    @Size(min = ValidationConstants.EmailSubject.MIN, max = ValidationConstants.EmailSubject.MAX)
    private String subject;

    @NotNull
    @Size(min = ValidationConstants.EmailSubject.MIN,  max = ValidationConstants.EmailSubject.MAX)
    private String body;

    @NotNull
    private Boolean success;

    private String errorMessage;

    public static CreateMessageLog ok(@NotNull SimpleMailMessage mailMessage) {
        CreateMessageLog save = from(mailMessage);
        save.setSuccess(true);
        return save;
    }

    public static CreateMessageLog error(@NotNull SimpleMailMessage mailMessage, @NotNull String errorMessage) {
        CreateMessageLog save = from(mailMessage);
        save.setSuccess(false);
        save.setErrorMessage(errorMessage);
        return save;
    }

    private static CreateMessageLog from(SimpleMailMessage mailMessage) {
        CreateMessageLog save = new CreateMessageLog();
        save.setFrom(mailMessage.getFrom());
        save.setRecipients(CreateMessageLog.Recipient.from(mailMessage.getTo()));
        save.setBody(mailMessage.getText());
        save.setSubject(mailMessage.getSubject());
        return save;
    }

    public MailLogEntity toEntity() {
        String recipient = recipients.stream().map(Recipient::getEmail).collect(Collectors.joining(","));
        MailLogEntity entity = new MailLogEntity();
        entity.setRecipient(recipient);
        entity.setSubject(subject);
        entity.setBody(body);
        entity.setFrom(from);
        entity.setSuccess(success);
        entity.setErrorMessage(errorMessage);
        return entity;
    }

    @Getter
    @Setter
    static class Recipient {
        @NotNull
        @Pattern(regexp = ValidationConstants.Email.REGEX)
        private String email;

        public static List<Recipient> from(@NotNull String... emails) {
            return Arrays.stream(emails).map(Recipient::from).collect(Collectors.toList());
        }

        public static Recipient from(String email) {
            Recipient recipient = new Recipient();
            recipient.setEmail(email);
            return recipient;
        }
    }
}
