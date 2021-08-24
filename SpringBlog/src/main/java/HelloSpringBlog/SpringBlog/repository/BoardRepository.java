package HelloSpringBlog.SpringBlog.repository;

import HelloSpringBlog.SpringBlog.model.Board;
import HelloSpringBlog.SpringBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface BoardRepository extends JpaRepository<Board,Integer> {
}
