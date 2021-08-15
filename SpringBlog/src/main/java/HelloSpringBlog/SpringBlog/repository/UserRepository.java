package HelloSpringBlog.SpringBlog.repository;

import HelloSpringBlog.SpringBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//자동으로 bean으로 등록
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User,Integer> {


}
