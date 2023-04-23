package Java6.ASM;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import Java6.ASM.Entities.Account;
import Java6.ASM.service.AccountService;

@Configuration
@EnableWebSecurity 
public class SercutiryConfig {
    // Vì từ bản Spring Security 5.7.0-M2 đã loại bỏ WebSecurityConfigurerAdapter
    // nên chuyển sang cấu hình bảo mật dựa trên thành phần
    // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
    @Autowired
    AccountService acService;

    // @Autowired
    // BCryptPasswordEncoder bcPw;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return userName -> {
            try {
                Account ac = acService.findById(userName);
                String pass = encoder.encode(ac.getPassword());
                String[] role = ac.getAuthorities().stream().map(er -> er.getRole().getId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(userName).password(pass).roles(role).build();
            } catch (Exception e) {
                throw new UsernameNotFoundException(userName + " Not Found!");
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/oder/**").authenticated()
                .requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
                .requestMatchers("/rest/authorities").hasRole("DIRE").anyRequest().permitAll();

        http.formLogin().loginPage("/sercurity/login/form")
                .loginProcessingUrl("/sercurity/login")
                .defaultSuccessUrl("/sercurity/login/success", false).failureUrl("/sercurity/login/error");

        http.rememberMe().tokenValiditySeconds(86400);
        http.exceptionHandling().accessDeniedPage("/sercurity/unauthoried");

        http.logout().logoutUrl("/sercurity/logoff").logoutSuccessUrl("/sercurity/logoff/success");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
