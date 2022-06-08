package com.kramar.Inst.services.userDetails;

import com.kramar.Inst.core.models.user.Gender;
import com.kramar.Inst.core.models.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
    private Gender gender;
    private Set<UserEntity> subscribers;
    private Set<UserEntity> subscriptions;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(
            Long id,
            String login,
            String password,
            String lastName,
            String firstName,
            String nickname,
            Gender gender,
            Collection<? extends GrantedAuthority> authorities,
            Set<UserEntity> subscribers,
            Set<UserEntity> subscriptions
    )
    {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.gender =  gender;
        this.password = password;
        this.authorities = authorities;
        this.subscribers = subscriptions;
        this.subscriptions = subscriptions;
    }

    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getLastName(),
                user.getFirstName(),
                user.getNickname(),
                user.getGender(),
                authorities,
                user.getSubscribers(),
                user.getSubscriptions()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
