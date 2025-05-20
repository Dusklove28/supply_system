package com.ningling.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeVO {
    private Long noticeId;//公告id
    private String title;//标题
    private String content;//公告内容
    private Long authorId;//用户id
    private int status;//发布状态
    private int isTop;//是否置顶
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
