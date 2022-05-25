package task1.isdaha.util;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Component
public class Utils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public static String dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }


}
