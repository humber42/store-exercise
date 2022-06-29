package cu.hash.storeexercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authz-> authz
                                .antMatchers("/api/v1/login").permitAll()
                                .antMatchers("/h2-ui/**").permitAll()
                                .antMatchers(
                                        "/swagger-resources/**",
                                        "/swagger-ui/**",
                                        "/v2/api-docs",
                                        "/webjars/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .csrf().disable()
                .cors()
                .and()
                .headers().frameOptions().sameOrigin();

        return http.build();
    }


}
