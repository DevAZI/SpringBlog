package HelloSpringBlog.SpringBlog.controller.api;

import HelloSpringBlog.SpringBlog.config.auth.PrincipalDetail;
import HelloSpringBlog.SpringBlog.dto.ResponseDto;
import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Security;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {

        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {  //@RequestBody가 있어야 json데이터 받아올 수 있음

        userService.회원수정(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }








/*  //전통적 방법 로그인
    @PostMapping("/api/user/login")
    public ResponceDto<Integer> login(@RequestBody User user , HttpSession session;) {
        User principal =userService.로그인(user);   //principal(접근주체)

        if (principal != null) {
           session.setAttribute("principal", principal);
        }
        return new ResponceDto<Integer>(HttpStatus.OK.value(), 1);
    }

*/




}
