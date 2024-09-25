package net.heartheat.userApp.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Transient
    private int age;

    public int getAge() {
        return (this.dob != null) ? LocalDate.now().getYear() - dob.getYear() : 0;
    }
}
