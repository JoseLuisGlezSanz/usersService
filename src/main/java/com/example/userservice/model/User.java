package com.example.userservice.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonProperty("identificador del usuario")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 50)
    @JsonProperty("nombre de usuario")
    private String userName;

    @Column(name = "mail", nullable = false, length = 255)
    @JsonProperty("correo del usuario")
    private String mail;

    @Column(name = "phone", nullable = false, length = 10)
    @JsonProperty("telefono del usuario")
    private String phone;

    @Column(name = "name", nullable = false, length = 150)
    @JsonProperty("nombre completo del usuario")
    private String name;

    @Column(name = "password", nullable = false, length = 255)
    @JsonProperty("contraseña del usuario")
    private String password;

    @Column(name = "status", nullable = false)
    @JsonProperty("estado del usuario")
    private Integer status;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

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
}