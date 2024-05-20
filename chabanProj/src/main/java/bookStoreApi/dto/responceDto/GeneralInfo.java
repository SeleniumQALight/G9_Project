package bookStoreApi.dto.responceDto;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GeneralInfo {
    private String userId;
    private String username;
    private BooksDto[] books;
}
