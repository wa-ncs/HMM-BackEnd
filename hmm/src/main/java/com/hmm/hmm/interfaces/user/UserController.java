package com.hmm.hmm.interfaces.user;

import com.hmm.hmm.domain.user.User;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

  @PostMapping("/users")
  public ResponseEntity<?> create(
      @RequestBody User resource
  ) throws URISyntaxException {
    String url = "/users/1";
    return ResponseEntity.created(new URI(url)).body("{}");
  }
}
