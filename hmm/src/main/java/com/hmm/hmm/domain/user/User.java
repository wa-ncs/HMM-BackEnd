package com.hmm.hmm.domain.user;

import com.hmm.hmm.domain.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.hmm.hmm.domain.auth.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Builder
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String email;

  private String userName;

  private String nickName;

  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProvider snsType;

  private String snsId;


  @ColumnDefault("/")
  private String profile;

  @CreationTimestamp
  private LocalDateTime createdDate;

  @UpdateTimestamp
  private LocalDateTime updatedDate;

  private LocalDateTime deletedDate;
}