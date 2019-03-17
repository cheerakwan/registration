package registration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import registration.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findByEmailAddress(@Param("email") String email);
}
