package apiDemoQa.dto.responseDto;
import lombok.*;

@NoArgsConstructor
@Data //Getter + Setter
@ToString
@AllArgsConstructor
@Builder

public class BooksDto {

    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publish_date;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;
}
