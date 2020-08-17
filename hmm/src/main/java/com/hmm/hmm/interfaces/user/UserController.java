package com.hmm.hmm.interfaces.user;

import com.hmm.hmm.application.user.UserService;
import com.hmm.hmm.domain.user.User;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

  private final UserService userService;

  @PostMapping("/users")
  public ResponseEntity<?> create(
      @RequestBody User resource
  ) throws URISyntaxException {
    log.debug("[TEST] ..."+resource);
    User user = userService.registerUser(resource);
    String url = "/users/1";
    return ResponseEntity.created(new URI(url)).body("{}");
  }
}
