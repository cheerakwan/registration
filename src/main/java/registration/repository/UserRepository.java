package registration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import registration.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    User findByEmailAddress(String email);
}
