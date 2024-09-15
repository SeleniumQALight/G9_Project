package apiDemoQa.dto.responseDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString


public class UserInfoDto {
    private String userId;
    private String username;
    private BooksDto[] books;
}
