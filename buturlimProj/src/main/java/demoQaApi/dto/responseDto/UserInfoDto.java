package demoQaApi.dto.responseDto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String userId;
    private String username;
    private BooksDto[] books;
}
