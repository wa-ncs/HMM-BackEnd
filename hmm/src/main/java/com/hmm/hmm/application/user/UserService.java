package com.hmm.hmm.application.user;

import com.hmm.hmm.domain.user.User;
import com.hmm.hmm.domain.user.UserRepository;
import com.hmm.hmm.interfaces.user.UserExistedException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  UserRepository userRepository;

  PasswordEncoder secretEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder secretEncoder) {
    this.userRepository = userRepository;
    this.secretEncoder = secretEncoder;
  }

  public User registerUser(User resource) {
    String email = resource.getEmail();
    String password = resource.getPassword();
    String userName = resource.getUserName();
    String nickName = resource.getNickName();
    String snsType = resource.getSnsType();
    String snsId = resource.getSnsId();
    Optional<User> existed = userRepository.findByEmail(email);

    if(existed.isPresent()) {
      throw new UserExistedException(email);
    }

    String encodedSecret = secretEncoder.encode(password);

    User user = User.builder()
        .email(email)
        .userName(userName)
        .nickName(nickName)
        .password(encodedSecret)
        .snsType(snsType)
        .snsId(snsId)
        .createdDate(LocalDateTime.now())
        .updatedDate(LocalDateTime.now())
        .build();

    return userRepository.save(user);
  }

//  public User authenticate(String email, String secret) {
//    User user = userRepository.findByEmail(email)
//        .orElseThrow(() -> new EmailNotExistedException(email));
//
//    if(!secretEncoder.matches(secret, user.getSecret())){
//      throw new PasswordWrongException();
//    }
//
//    return user;
//  }
//

}
