package uz.inha.chatting_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message extends BaseEntity{
    @NotBlank(message = "message cannot be blank")
    private String text;
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
    @CreationTimestamp
    private Timestamp dateTime;
}
