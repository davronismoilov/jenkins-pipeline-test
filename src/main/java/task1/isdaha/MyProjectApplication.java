package task1.isdaha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import task1.isdaha.config.SwaggerConfig;
import task1.isdaha.config.WebConfig;
import task1.isdaha.repository.RoleRepository;

@SpringBootApplication

@EnableJpaRepositories(value = "task1")
@EntityScan(value = "task1")
@EnableSwagger2
@Import({SwaggerConfig.class , WebConfig.class})
public class MyProjectApplication implements CommandLineRunner {
//    @Autowired
//    RoleRepository roleRepository;




    public static void main(String[] args) {
        SpringApplication.run(MyProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        roleRepository.save(new Role(1L , RoleEnum.USER));
//        roleRepository.save(new Role(2L, RoleEnum.MANAGER));


    }
}
