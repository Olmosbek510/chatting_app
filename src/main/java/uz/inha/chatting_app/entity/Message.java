package uz.inha.chatting_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message extends BaseEntity{
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
}
