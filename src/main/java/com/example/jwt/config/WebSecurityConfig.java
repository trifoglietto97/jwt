package com.example.jwt.config;

//config cors e csrf


import com.example.jwt.jwt.AuthEntryPointJwt;
import com.example.jwt.jwt.AuthTokenFilter;
import com.example.jwt.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity( //consente a Spring di trovare e applicare automaticamente la classe alla Web Security globale.

        prePostEnabled = true ) //@PreAuthorize preautorizzazione per decidere se una chiamata è consentita o meno
public class WebSecurityConfig {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;  //gestore di eccezzioni

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() { // Indica a Spring Security come configuriamo CORS e CSRF, quando vogliamo richiedere che tutti gli utenti siano autenticati o meno, quale filtro
    return new AuthTokenFilter();
  }

  //UserDetailsServiceverrà utilizzata per la configurazione DaoAuthenticationProvidertramite AuthenticationManagerBuilder.userDetailsService()metodo.
//Abbiamo anche bisogno di un PasswordEncoderper il DaoAuthenticationProvider. Se non lo specifichiamo, utilizzerà il testo normale.
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            //inizio da personalizzare
            .authorizeRequests().requestMatchers("/api/auth/**").permitAll()//dice quale chiamata autorizzare
            .requestMatchers("/api/test/**").permitAll()
            .anyRequest().authenticated();
    //fine da personalizzare

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

