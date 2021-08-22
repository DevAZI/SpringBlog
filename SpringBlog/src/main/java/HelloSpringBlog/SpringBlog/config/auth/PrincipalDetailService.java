package HelloSpringBlog.SpringBlog.config.auth;

import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //로그인 요청시 username,password 변수 2개를 가로챌때 password는 알아서 처리함 & username이 db에 있는지 확인하면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User principal = userRepository.findByUsername(username)
        .orElseThrow(()->{
            return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다.");
        });
        return new PrincipalDetail(principal);
    }
}
