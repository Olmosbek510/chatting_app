package uz.inha.chatting_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User extends BaseEntity{
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phone;
    private String password;
    @OneToOne
    private Attachment photo;
}
