package com.ningling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeReleaseDTO {
    private String title;
    private String content;
    private int status;
    private int isTop;
    private Long authorId;
}
