package com.blmeena.rosters.models;

import com.blmeena.rosters.models.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@DynamicUpdate
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"user_likes"})
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_name"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "encrypted_password")
    private String password;

    @OneToMany(mappedBy="to")
    private List<UserLike> followers;

    @OneToMany(mappedBy="from")
    private List<UserLike> following;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Profile profile;
}