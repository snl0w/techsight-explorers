package br.com.tcse.repository;

import br.com.tcse.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findByTitle(String title);
}
