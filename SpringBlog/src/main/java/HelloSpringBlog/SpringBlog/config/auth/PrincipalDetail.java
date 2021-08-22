package HelloSpringBlog.SpringBlog.config.auth;

import HelloSpringBlog.SpringBlog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 선점하여 로그인을 진행한후 완료되면, UserDetail타입의 오브젝트를 고유한 세션저장소에 저장해줌
public class PrincipalDetail implements UserDetails {
    private User user;//컴포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //계정이 만료되지 않았는지 리턴 // true = 만료안됨
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정이 잠겨있는지 true = 안잠김
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비밀번호가 만료되었는지
        return true;
    }

    @Override
    public boolean isEnabled() { //계정이 활성화 인지
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//계정의 권한

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return "ROLE_"+user.getRole();});
        return collectors;
    }


}
 /* //람다식 으로변환 전
 collectors.add(new GrantedAuthority() {
@Override
public String getAuthority() {
        return "ROLE_" + user.getRole(); //ROLE_ <<스프링 규칙임  ->>ex ROLE_USER
        }
        });*/