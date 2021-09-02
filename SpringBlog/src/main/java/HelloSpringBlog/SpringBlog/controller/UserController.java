package HelloSpringBlog.SpringBlog.controller;

import HelloSpringBlog.SpringBlog.config.auth.PrincipalDetail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
    //인증이 안된 사용자들이 출입할 수 있는 경로 /auth & /
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";

    }
    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";

    }
    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
        return "user/updateForm";

    }
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id","fd7773aa9c185ba0418f2481e1987c73");
        params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
        params.add("code", code);
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //HttpHeaders & HttpBody를 하나의 오브젝트에 담기
        ResponseEntity <String>response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return "카카오인증완료";

    }
}
