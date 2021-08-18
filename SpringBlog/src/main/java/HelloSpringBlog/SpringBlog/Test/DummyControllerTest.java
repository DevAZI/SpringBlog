package HelloSpringBlog.SpringBlog.Test;

import HelloSpringBlog.SpringBlog.model.RoleType;
import HelloSpringBlog.SpringBlog.model.User;
import HelloSpringBlog.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //DI(의존성 주입)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/id/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "실패";
        }

        return "변경되었습니다."+id;
    }



    @Transactional //함수 종료시 자동 commit됨
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

        System.out.println("id: " + id);
        System.out.println("pw: " + requestUser.getPassword());
        System.out.println("em: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        }); //영속화
        requestUser.setPassword(requestUser.getPassword()); // 영속화되고 값이 변경되면 update -> dirty chekcing(상태변경 검사)
        requestUser.setEmail(requestUser.getEmail());
        //userRepository.save(user);
        //save함수는  id를 전달하지 않으면 insert, 해당하는 데이터가 있으면 update


    return user;
    }


    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한페이지당 2건씩의 데이터를 가져옴
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size =2, sort="id",
            direction = Sort.Direction.DESC)Pageable pageable) {

       Page<User> pagingUsers = userRepository.findAll(pageable);

       List<User> users = pagingUsers.getContent();
       return users;
    }


    //{id}주소로 파라미터 전달 받을 수 있음
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당되는 유저는 없습니다. id:" + id);
            }
            //람다식
            //()->{
            //return new IllegalArgumentException("해당되는 유저는 없습니다.);
            //});
        });
        return user;
    }


    @PostMapping("/dummy/join")
    public String join(User user)
    {
        System.out.println("id"+ user.getId());
        System.out.println("un"+ user.getUsername());
        System.out.println("pw"+user.getPassword() );
        System.out.println("em"+ user.getEmail());
        System.out.println("ro"+ user.getRole());
        System.out.println("cr"+ user.getCreateDate());

        user.setRole(RoleType.USER);

        userRepository.save(user);






        return "회원가입이 완료되었습니다.";

    }



}
