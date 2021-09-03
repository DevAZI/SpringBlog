package HelloSpringBlog.SpringBlog.service;

import HelloSpringBlog.SpringBlog.model.RoleType;
import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통하여 bean에 등록해줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }


    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); //원본
        String encPassword = encode.encode(rawPassword); //해쉬

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        //수정시 영속화를 통하여 user오브젝트를 영속화 시키고, 영속화된 오브젝트를 수정
        //select해서 user오브젝트를 db로부터 가져와 영속화를 시켜줌
        //영속화된 오브젝트 수정시 자동으로 update해줌

        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");

        });
        String rawPassword = user.getPassword();
        String encPassword = encode.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());



    }
    /*@Transactional(readOnly = true ) // select 할때 트랜젝션 시작, 서비스 종료시 트랜잭션 종료 ( 정합성 )
    public User 로그인(User user) {
       return  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}


