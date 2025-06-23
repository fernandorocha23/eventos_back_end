package pt.iscte.event;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityWebConfig {

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(corsConfigurer -> {
            corsConfigurer.configurationSource(corsConfigurationSource());
        });
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
            auth.requestMatchers(HttpMethod.POST, "/login", "/user").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/artists", "/artists/*", "/events", "/events/*", "/artists/*/comments").permitAll();
            auth.requestMatchers(HttpMethod.POST,"/artists", "/events").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.GET, "/user/comments", "/logout", "/user/logado", "/users/{id}/profile").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/artists/*/comments", "/comments/*/like", "/comments/*/dislike").authenticated();
            auth.requestMatchers("**").denyAll();
        });
        httpSecurity.formLogin(loginConfig -> {
            loginConfig.loginProcessingUrl("/login");
            loginConfig.successHandler((request, response, authentication) -> {
                response.setStatus(200);
            });
            loginConfig.failureHandler((request, response, authentication) -> {
                response.setStatus(401);
            });
        });
        httpSecurity.logout(logoutConfig -> {
            logoutConfig.logoutUrl("/logout");
            logoutConfig.deleteCookies("JSESSIONID");
            logoutConfig.logoutSuccessUrl("/");
            logoutConfig.logoutSuccessHandler((request, response, authentication) -> {
               response.setStatus(200);
            });
        });
        httpSecurity.authenticationProvider(userAuthenticationProvider);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
