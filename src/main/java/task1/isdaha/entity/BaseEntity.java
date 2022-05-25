package task1.isdaha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(columnDefinition = "boolean default true")
    private boolean isActive = true;

    @CreationTimestamp
    @JsonIgnore
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedDate;

    @CreatedBy
    @Column(updatable  = false)
    private T createdBy;

    @LastModifiedBy
    @JsonIgnore
    private T updatedBy;

    public BaseEntity() {
    }
}
