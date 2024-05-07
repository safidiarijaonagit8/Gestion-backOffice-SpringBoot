package articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import articles.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
