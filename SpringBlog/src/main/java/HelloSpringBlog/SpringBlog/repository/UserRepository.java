package HelloSpringBlog.SpringBlog.repository;

import HelloSpringBlog.SpringBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


//자동으로 bean으로 등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);


}
//전통적 방법
//SELECT * FROM user WHRER username  = ?1 AND password = ?2;
// User findByUsernameAndPassword(String username, String password);


/*    @Query(value = "SELECT * FROM user WHERE username  = ?1 AND password = ?2", nativeQuery = true)
    User login(String username, String password);*/
