package com.example.elonuz.domains;

import com.example.elonuz.enums.Role;
import com.example.elonuz.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends Auditable{

    @Column(name = "full_name")
    private String fullName;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.NOT_ACTIVE;

    @Column(nullable = false)
    private boolean enabled;
}
