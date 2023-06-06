package test.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idEntityGenerator")
    @SequenceGenerator(name = "idEntityGenerator", sequenceName = "id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID();
        active = true;
    }
}
