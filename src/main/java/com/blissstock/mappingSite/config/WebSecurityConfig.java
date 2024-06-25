package com.blissstock.mappingSite.config;

import com.blissstock.mappingSite.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * SpringSecurityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
 * 
 * @author aoi
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  // フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  /**
   * 認可設定を無視するリクエストを設定
   * 静的リソース(image,javascript,css)を認可処理の対象から除外する
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    System.out.println("Ignoring");
    web.ignoring().antMatchers("/images/**", "/css/**", "/js/**");
  }

  /**
   * 認証・認可の情報を設定する
   * 画面遷移のURL・パラメータを取得するname属性の値を設定
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/register/**", "/login/**", "/password/**", "/home/**", "/verify_password/**", "/resetPassword")
        .permitAll()
        .antMatchers("/guest/**", "/**/change_password", "/change_password")
        .permitAll()
        .antMatchers("/teacherAccount/register/complete", "/studentAccount/register/complete")
        .permitAll()
        .antMatchers("/check_email/**", "/check_email/admin/register", "confirmation")
        .permitAll()
        .antMatchers("/images/**", "/css/**", "/js/**")
        .permitAll()
        .antMatchers("/syllabus_details/course_id/**")
        .permitAll()
        .antMatchers("/files/**")
        .permitAll()
        .antMatchers("/student/**")
        .hasAnyAuthority(UserRole.STUDENT.getValue())
        .antMatchers("/teacher/**")
        .hasAnyAuthority(UserRole.TEACHER.getValue())
        .antMatchers("/admin/**")
        .hasAnyAuthority(
            UserRole.ADMIN.getValue(),
            UserRole.SUPER_ADMIN.getValue())
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login") // ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
        .loginProcessingUrl("/sign_in") // フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
        .usernameParameter("email") // リクエストパラメータのname属性を明示
        .passwordParameter("password")
        .successForwardUrl("/home")
        .failureHandler(authenticationFailureHandler()) // ログインURL失敗した時実行する
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
        .permitAll();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login")
        .maximumSessions(1).sessionRegistry(sessionRegistry())
        .expiredUrl("/login");
    http.csrf().disable();
  }

  /**
   * 認証時に利用するデータソースを定義する設定メソッド
   * ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている
   * 
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * We need this bean for the session management. Specially if we want to control
   * the concurrent session-control support
   * with Spring security.
   * 
   * @return
   */
  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new MyAuthenticationFailureHandler();
  }
}
