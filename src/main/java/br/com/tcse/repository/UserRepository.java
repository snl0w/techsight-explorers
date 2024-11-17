package br.com.tcse.repository;

import br.com.tcse.model.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserBlog, Long> {

    // Método customizado para encontrar um usuário pelo email
    UserBlog findByEmail(String email);
}
