package com.blissstock.mappingSite.entity;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {

  private Long id;

  public CustomUser(
    String username,
    String password,
    boolean enabled,
    boolean accountNonExpired,
    boolean credentialsNonExpired,
    boolean accountNonLocked,
    Collection<? extends GrantedAuthority> authorities
  ) {
    super(
      username,
      password,
      enabled,
      accountNonExpired,
      credentialsNonExpired,
      accountNonLocked,
      authorities
    );
  }

  public CustomUser(
    String username,
    String password,
    Collection<? extends GrantedAuthority> authorities
  ) {
    super(username, password, true, true, true, true, authorities);
  }

  public CustomUser(
    Long id,
    String username,
    String password,
    Collection<? extends GrantedAuthority> authorities
  ) {
    super(username, password, true, true, true, true, authorities);
    this.id = id;
  }


}
