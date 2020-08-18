package com.hmm.hmm.interfaces.auth;

import com.hmm.hmm.application.user.UserService;
import com.hmm.hmm.domain.user.User;
import com.hmm.hmm.interfaces.auth.dto.LoginRequest;
import com.hmm.hmm.utils.JwtUtil;
import com.hmm.hmm.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final TokenProvider tokenProvider;

    //

    @PostMapping("/login")
    public ResponseEntity<?> create(@RequestBody LoginRequest resource) {

        String email = resource.getEmail();
        String password = resource.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);

        return ResponseEntity.ok(new AuthResponse(token));
//        User user = userService.authenticate(email, password);
//        String accessToken = jwtUtil.createToken(user.getUserId(), user.getNickName());
//        String url = "/token";
//        return ResponseEntity.created(new URI(url)).body(accessToken);
    }
}
