package pl.pwr.scent_tracker.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.pwr.scent_tracker.security.api.ApiJWTAuthenticationFilter;
import pl.pwr.scent_tracker.security.api.ApiJWTAuthorizationFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiSecurityConfig {

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Bean
        public AuthenticationManager authenticationManager() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(bCryptPasswordEncoder);
            return provider::authenticate;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
            return http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/**")
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                //.authenticated()
                                .permitAll() //TODO
                )
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .authenticationEntryPoint(
                                        (req, rsp, ex) -> rsp
                                                .sendError(HttpServletResponse.SC_UNAUTHORIZED)
                                )
                )
                .addFilterBefore(new ApiJWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new ApiJWTAuthorizationFilter(authenticationManager), BasicAuthenticationFilter.class)
                .sessionManagement(
                        (sessionManagement) -> sessionManagement.
                                sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
        }
    }

//    @Configuration
//    @Order(2)
//    public static class FormLoginWebSecurityConfigurerAdapter {
//        @Autowired
//        private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//        @Autowired
//        private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//        @Autowired
//        private CustomUserDetailsService userDetailsService;
//
//        @Bean
//        public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//            provider.setUserDetailsService(userDetailsService);
//            provider.setPasswordEncoder(passwordEncoder);
//            return provider::authenticate;
//        }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            return http
//                    .cors(Customizer.withDefaults())
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .authorizeHttpRequests((auth) -> auth
//                            .requestMatchers("/").permitAll()
//                            .requestMatchers("/login").permitAll()
//                            .requestMatchers("/signup").permitAll()
//                            .requestMatchers("/dashboard/**").hasAuthority("ADMIN")
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin((form) -> form
//                            .loginPage("/login").permitAll()
//                            .failureUrl("/login?error=true")
//                            .usernameParameter("login")
//                            .passwordParameter("password")
//                            .successHandler(customAuthenticationSuccessHandler)
//                    )
//                    .logout((logout) -> logout
//                            .permitAll()
//                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                            .logoutSuccessHandler(new CustomLogoutSuccessHandler())
//                            .deleteCookies("JSESSIONID")
//                            .logoutSuccessUrl("/")
//                    )
//                    .exceptionHandling(Customizer.withDefaults())
//                    .build();
//        }
//
//        @Bean
//        public WebSecurityCustomizer webSecurityCustomizer() {
//            return (web) -> web.ignoring().requestMatchers(
//                    "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
//                    "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
//                    "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
//                    "/v2/api-docs", "/configuration/ui", "/configuration/security",
//                    "/webjars/**", "/swagger-resources/**", "/actuator", "/swagger-ui/**",
//                    "/actuator/**", "/swagger-ui/index.html", "/swagger-ui/"
//            );
//        }
//
//    }
}
