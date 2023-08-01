package pl.kurs.shapesapp.repositories;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.shapesapp.models.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.shapes")
    List<User> findUsersWithShapes();
}
