package br.com.tcse.repository;

import br.com.tcse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método customizado para encontrar um usuário pelo email
    User findByEmail(String email);

    //Método para encontrar um usuário pelo username
    User findByUsername(String username);
}
