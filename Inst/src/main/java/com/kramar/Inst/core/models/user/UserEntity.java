package com.kramar.Inst.core.models.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kramar.Inst.core.models.Post;
import com.kramar.Inst.core.models.role.RoleEntity;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(max = 40)
    private String login;

    @NotBlank
    @Size(min = 2)
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 5)
    @Size(max = 20)
    @Column(unique = true)
    private String nickname;

    @NotBlank
    @Size(min = 9)
    @Size(max = 40)
    private String password;

    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @Nullable
    public Set<UserEntity> subscriptions = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @Nullable
    public Set<UserEntity> subscribers = new HashSet<>();

    @Setter(AccessLevel.PROTECTED)
    @ToString.Exclude
    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL)
    private List<Post> posts;

}
