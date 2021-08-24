package HelloSpringBlog.SpringBlog.service;

import HelloSpringBlog.SpringBlog.model.Board;
import HelloSpringBlog.SpringBlog.model.RoleType;
import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.repository.BoardRepository;
import HelloSpringBlog.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//스프링이 컴포넌트 스캔을 통하여 bean에 등록해줌
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void 글쓰기(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> 글목록 (Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    /*@Transactional(readOnly = true ) // select 할때 트랜젝션 시작, 서비스 종료시 트랜잭션 종료 ( 정합성 )
    public User 로그인(User user) {
       return  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}


