package net.suhel.registrationlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true ,nullable = false)
    private String email;
@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(
        name="users_roles",
        joinColumns ={ @JoinColumn(name="user_id",referencedColumnName = "id") },
        inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")}
)
    private List<Role> roles= new ArrayList<>();
}
