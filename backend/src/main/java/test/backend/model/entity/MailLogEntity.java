package test.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mail_log")
public class MailLogEntity extends AbstractEntity{

    @Column(name = "recipient", nullable = false)
    private String recipient;
    @Column(name = "sender", nullable = false)
    private String from;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private Boolean success;
    private String errorMessage;
}
