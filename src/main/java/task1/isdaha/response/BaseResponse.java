package task1.isdaha.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BaseResponse {
        // -10 already exist
        Response ALREADY_EXIST = new Response("Already exist"  , -10);

        Response SUCCESS = new Response("Successfully"  ,  10);

        Response USER_NOT_FOUND = new Response("User not found " , -20);

        Response NOT_FOUND = new Response(" not found " , -21);
}
