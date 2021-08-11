package HelloSpringBlog.SpringBlog.Test;

import org.springframework.web.bind.annotation.*;

//Controller  = 응답(HTML)
//RestController = 응답(Data)

@RestController
public class HttpControllerTest {

    //브라우저를 통하여 요청은 get요청밖에 할 수 없다.
    @GetMapping("/http/get")
    public String getTest(@RequestParam int id) {
        return "get 요청 :" + id;
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {

        return "post 요청" +m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
    }
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }


}
