package task1.isdaha.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getUser")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> getUser() {

        return new SpringSecurityAuditAwareImp();
    }
}
