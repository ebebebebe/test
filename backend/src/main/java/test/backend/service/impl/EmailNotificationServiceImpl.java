package test.backend.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import test.backend.model.dto.CreateMessageLog;
import test.backend.model.dto.NotifyCreateUser;
import test.backend.service.EmailNotificationService;
import test.backend.service.MailLogService;

@Service
@Validated
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {
    private final JavaMailSender mailSender;
    private final MailLogService mailLogService;

    @Value("${internal.service.mail.from}")
    private String emailFrom;

    @Override
    public void notifyCreateUser(NotifyCreateUser user) {
        SimpleMailMessage mailMessage = MessageBuilder.builder()
                .to(user.getEmail())
                .from(emailFrom)
                .subject("Welcome!")
                .body("Thank you for registering!")
                .build()
                .toSimpleMailMessage();

        try {
            mailSender.send(mailMessage);
            mailLogService.save(CreateMessageLog.ok(mailMessage));
        } catch (MailException e) {
            mailLogService.save(CreateMessageLog.error(mailMessage, e.getMessage()));
        }
    }

    @Builder
    static class MessageBuilder {
        private String to;
        private String from;
        private String subject;
        private String body;

        public SimpleMailMessage toSimpleMailMessage() {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setFrom(from);
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            return mailMessage;
        }
    }
}
