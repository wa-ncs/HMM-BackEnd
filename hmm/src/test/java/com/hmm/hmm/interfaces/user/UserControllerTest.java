package com.hmm.hmm.interfaces.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

  @Autowired
  MockMvc mvc;

  @Test
  public void create() throws Exception {
    mvc.perform(post("/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"tester@example.com\",\"userName\":\"Tester\",\"nickName\":\"Tester\" \"password\":\"test\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location","/users/1"));
  }
}