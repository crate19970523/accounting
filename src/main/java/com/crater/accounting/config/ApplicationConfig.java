package com.crater.accounting.config;

import com.crater.accounting.bean.database.UserRedisDataPojo;
import com.crater.accounting.security.AccountingBearerTokenResolver;
import com.crater.accounting.security.BearerTokenAuthenticationProviderImpl;
import com.crater.craterlogin.bean.entity.redis.TokenPojo;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ApplicationConfig {
    private String contextPath;

    @Bean
    public AccountingBearerTokenResolver accountingBearerTokenResolver() {
        return new AccountingBearerTokenResolver();
    }

    @Bean
    public AuthenticationProvider bearerTokenAuthenticationProvider() {
        return new BearerTokenAuthenticationProviderImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider bearerTokenAuthenticationProvider,
                                                   AccountingBearerTokenResolver accountingBearerTokenResolver) throws Exception {
        var oauth2Manger = new ProviderManager(Collections.singletonList(bearerTokenAuthenticationProvider));
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .oauth2ResourceServer(h ->
                        h.bearerTokenResolver(accountingBearerTokenResolver).authenticationManagerResolver(request -> oauth2Manger))
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "account/login", "/accountController/account",
                "swagger-ui/**", "/swagger-ui.html", "open-api/**", "/v3/api-docs/**");
    }

    @Bean
    public RedisTemplate<String, TokenPojo> tokenRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, TokenPojo> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisTemplate<String, UserRedisDataPojo> userRedisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, UserRedisDataPojo> userRedisTemplate = new RedisTemplate<>();
        userRedisTemplate.setConnectionFactory(factory);

        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        userRedisTemplate.setKeySerializer(stringRedisSerializer);
        userRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        userRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        userRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        userRedisTemplate.afterPropertiesSet();
        return userRedisTemplate;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("token"))
                )
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info()
                        .title("記帳！？")
                        .description("自動記帳")
                        .version("0.0.0")
                        .contact(new Contact().name("王郁翔").email("s19970523s@gmail.com")));
    }

    @Value("${server.servlet.context-path}")
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
