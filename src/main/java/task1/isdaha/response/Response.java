package task1.isdaha.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@Builder
public class Response {
    String message;
    int statusCode;
    Object data;

    public Response(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
