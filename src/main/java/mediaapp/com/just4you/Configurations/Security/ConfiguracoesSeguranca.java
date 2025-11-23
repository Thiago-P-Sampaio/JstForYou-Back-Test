package mediaapp.com.just4you.Configurations.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ConfiguracoesSeguranca implements WebMvcConfigurer {

    @Autowired
    FiltroSeguranca filtroSeguranca;

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         return  httpSecurity
                 .csrf(csrf -> csrf.disable())
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authorizeHttpRequests(authorize -> authorize
                                 .requestMatchers(HttpMethod.GET, "/swagger**/**").hasRole("ADMIN")
                                 .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                 .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                                 .requestMatchers(HttpMethod.POST, "/auth/forgot-password").permitAll()
                                 .requestMatchers(HttpMethod.POST, "/auth/reset-password").permitAll()
                                 .anyRequest().authenticated()
                 )

                 .addFilterBefore(filtroSeguranca, UsernamePasswordAuthenticationFilter.class )
                 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                 .build();
     }

    @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
     }


     @Bean
     public PasswordEncoder CriptSenha (){
        return  new BCryptPasswordEncoder();
     }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://justforyoutcc.netlify.app");
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
