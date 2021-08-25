package HelloSpringBlog.SpringBlog.service;

import HelloSpringBlog.SpringBlog.config.auth.PrincipalDetail;
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

import java.security.Principal;
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
    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }
    @Transactional
    public void 글수정하기(int id, Board requestBoard, PrincipalDetail principal) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패");
                }); // 영속화 완료
        if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalArgumentException("해당글을 삭제할 권한이 없습니다.");
        }
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }
    @Transactional
    public void 글삭제하기(int id, PrincipalDetail principal) {

        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
        if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalArgumentException("해당글을 삭제할 권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Page<Board> 글목록 (Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    /*@Transactional(readOnly = true ) // select 할때 트랜젝션 시작, 서비스 종료시 트랜잭션 종료 ( 정합성 )
    public User 로그인(User user) {
       return  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}


