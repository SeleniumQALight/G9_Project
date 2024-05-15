package api.dto.requestDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class CreatePostDto { //поля брати з документації
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;

}
