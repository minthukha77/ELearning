package com.blissstock.mappingSite.service;

import java.util.HashSet;
import java.util.Set;

import com.blissstock.mappingSite.entity.CustomUser;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.enums.AccountStatus;
import com.blissstock.mappingSite.exceptions.NonMailVerifiedUserException;
import com.blissstock.mappingSite.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserAccountRepository userAccountRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    UserAccount user;
    try {
      user = userAccountRepository.findByMail(email);
      System.out.println(user.getRole());
    } catch (Exception e) {
      throw new UsernameNotFoundException(e.getMessage());
    }

    Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
    if(user.getAccountStatus().equals(AccountStatus.SUSPENDED.getValue())){
      throw new DisabledException("Account id "+user.getAccountId()+" is Suspended");
    }
    if(!user.isMailVerified()){
      throw new NonMailVerifiedUserException("Account id "+user.getAccountId()+" has not verified the email yet!");
    }
    return new CustomUser(
      user.getAccountId(),
      user.getMail(),
      user.getPassword(),
      grantedAuthorities
    );
  }
}
