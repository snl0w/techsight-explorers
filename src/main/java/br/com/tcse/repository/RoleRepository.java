package br.com.tcse.repository;

import br.com.tcse.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    // Método customizado para encontrar uma Role pelo nome
    Role findByName(String name);
}
