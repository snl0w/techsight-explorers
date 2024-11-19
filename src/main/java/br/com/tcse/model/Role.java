package br.com.tcse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Nome da role (ex: "ADMIN", "USER")

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    // Construtor alternativo que inicializa apenas o nome da role
    public Role(String name) {
        this.name = name;
    }
}
