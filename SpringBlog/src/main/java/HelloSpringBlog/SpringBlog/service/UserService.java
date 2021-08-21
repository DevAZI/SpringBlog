package HelloSpringBlog.SpringBlog.service;

import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통하여 bean에 등록해줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true ) // select 할때 트랜젝션 시작, 서비스 종료시 트랜잭션 종료 ( 정합성 )
    public User 로그인(User user) {
       return  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}


