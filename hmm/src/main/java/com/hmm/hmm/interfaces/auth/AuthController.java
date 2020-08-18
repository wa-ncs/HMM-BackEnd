package com.hmm.hmm.interfaces.auth;

import com.hmm.hmm.application.user.UserService;
import com.hmm.hmm.domain.user.User;
import com.hmm.hmm.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthController {


    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<String> create(@RequestBody AuthRequestDto resource) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(user.getUserId(), user.getNickName());

        String url = "/token";

        return ResponseEntity.created(new URI(url)).body(accessToken);
    }
}
