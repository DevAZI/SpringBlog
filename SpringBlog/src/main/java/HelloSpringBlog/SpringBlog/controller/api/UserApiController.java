package HelloSpringBlog.SpringBlog.controller.api;

import HelloSpringBlog.SpringBlog.dto.ResponceDto;
import HelloSpringBlog.SpringBlog.model.RoleType;
import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

    @PostMapping("/api/user")
    public ResponceDto<Integer> save(@RequestBody User user) {
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponceDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/user/login")
    public ResponceDto<Integer> login(@RequestBody User user ) {
        User principal =userService.로그인(user);   //principal(접근주체)

        if (principal != null) {
           session.setAttribute("principal", principal);
        }


        return new ResponceDto<Integer>(HttpStatus.OK.value(), 1);
    }





}
