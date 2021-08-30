package HelloSpringBlog.SpringBlog.controller.api;

import HelloSpringBlog.SpringBlog.config.auth.PrincipalDetail;
import HelloSpringBlog.SpringBlog.dto.ResponseDto;
import HelloSpringBlog.SpringBlog.model.Board;
import HelloSpringBlog.SpringBlog.service.BoardService;
import HelloSpringBlog.SpringBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @DeleteMapping("/api/board/{id}")
    //@RequestMapping(value="/api/board/{id}", method=RequestMethod.DELETE)
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글삭제하기(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        System.out.println("BoardApiController : update : id : "+id);
        System.out.println("BoardApiController : update : board : "+board.getTitle());
        System.out.println("BoardApiController : update : board : "+board.getContent());
        boardService.글수정하기(id, board, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }



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





