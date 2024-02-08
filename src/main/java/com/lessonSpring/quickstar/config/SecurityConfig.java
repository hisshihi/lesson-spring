package com.lessonSpring.quickstar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Включаем web безопастность
@EnableWebSecurity
public class SecurityConfig {

    @Value("${lessonSpring.quickstar.api-key.key}")
    private String principalRequestHeader;

    @Value("${lessonSpring.quickstar.api-key.value}")
    private String principalRequestValue;
    @Bean
//    Создаём в память менеджер для пользователей
    public UserDetailsService userDetailsService() {

//        Создаём в памяти менеджера пользователей и создаём самого пользователя
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    // HttpSecurity предоставляет методы для настройки различных аспектов безопасности,
    // таких как аутентификация, авторизация и управление сеансами.
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        Внутри метода создается экземпляр ApiKeyAuthFilter с использованием параметра principalRequestHeader.
//        Этот заголовок должен содержать имя ожидаемого API-ключа.
        final ApiKeyAuthFilter filter = new ApiKeyAuthFilter(principalRequestHeader);

//        AuthenticationManager отвечает за проверку учетных данных, предоставленных клиентом.
//        Здесь он проверяет, совпадает ли principal,
//        извлеченный из объекта аутентификации (который должен быть значением API-ключа),
//        с ожидаемым значением (principalRequestValue).
        filter.setAuthenticationManager((Authentication authentication) -> {
            final String principal = (String) authentication.getPrincipal();
            if (!principalRequestValue.equals(principal)) {
                throw new BadCredentialsException("Данные пользователя не верные");
            }
//            Если они совпадают, аутентификация помечается как успешная,
//            в противном случае происходит выброс BadCredentialsException.
            authentication.setAuthenticated(true);
            return authentication;
        } );

//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        настраивает Spring Security на отказ от создания HTTP-сеанса для каждого запроса.
//        Это подходит для API-приложений, где состояние сеанса не требуется.
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
//                httpSecurity.addFilterBefore(filter) добавляет ApiKeyAuthFilter в цепочку фильтров безопасности перед другими фильтрами безопасности.
//                Это гарантирует, что API-ключ будет проверен до других проверок безопасности.
                .addFilter(filter).authorizeHttpRequests()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }

}
