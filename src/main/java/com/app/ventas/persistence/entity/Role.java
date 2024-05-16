package com.app.ventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    /*@ManyToMany
    @JoinTable(name ="granted_permission",
                joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )*/
    @JsonIgnoreProperties("role")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    //luego podriamos cambiarlo por una lista pero los set no te permiten repetirce
    private Set<GrantedPermission> grantedPermission;


    @Override
    public String getAuthority() {
        if(name == null) return null;

        return "ROLE_"+name.name();
    }

    @Getter
    public static enum RoleEnum{
        ADMIN,CUSTOMER, EDITOR, AUDITOR
    }
}
